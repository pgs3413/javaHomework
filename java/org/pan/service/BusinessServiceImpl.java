package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.*;
import org.pan.mapper.BusinessMapper;
import org.pan.mapper.GoodsMapper;
import org.pan.mapper.TradeMapper;
import org.pan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    BusinessMapper businessMapper;
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;


    public List<Business> busCheck() {
        QueryWrapper<Business> wrapper=new QueryWrapper<Business>();
        wrapper.eq("status",0);
        List<Business> list=businessMapper.selectList(wrapper);
        return list;
    }

    @Override
    public int busPass(int id) {
        Business business=new Business();
        business.setId(id);
        business.setStatus(1);
        return businessMapper.updateById(business);
    }

    @Override
    public int busFail(int id) {
        Business business=new Business();
        business.setId(id);
        business.setStatus(-1);
        return businessMapper.updateById(business);
    }

    @Override
    public boolean businessLogin(String phone, String password) {
        QueryWrapper<Business> wrapper=new QueryWrapper<Business>();
        wrapper.eq("phone",phone);
        wrapper.eq("password",password);
        wrapper.eq("status",1);
        int result=businessMapper.selectCount(wrapper);
        if(result>0) return true;
        else return false;
    }

    @Override
    public long getIdByPhone(String phone) {
        QueryWrapper<Business> wrapper=new QueryWrapper<Business>();
        wrapper.eq("phone",phone);
        Business business=businessMapper.selectOne(wrapper);
        return business.getId();
    }

    @Override
    public Business getBusinessById(int id) {
        return businessMapper.selectById(id);

    }

    @Override
    public List<Order> getOrder(int businessId) {
        List<Order> list=new ArrayList<Order>();
        QueryWrapper<Trade> wrapper=new QueryWrapper<Trade>();
        wrapper.eq("business_id",businessId);
        List<Trade> tradeList=tradeMapper.selectList(wrapper);
        for(Trade trade:tradeList){
            Order order=new Order();
            Goods goods=goodsMapper.selectById(trade.getGoodsId());
            User user =userMapper.selectById(trade.getUserId());
            order.setTrade(trade);
            order.setGoods(goods);
            order.setUser(user);
            list.add(order);
        }
        return list;
    }

    @Override
    public int addBusiness(Business business) {
        QueryWrapper<Business> wrapper=new QueryWrapper<>();
        wrapper.eq("phone",business.getPhone());
        int result=businessMapper.selectCount(wrapper);
        if(result>0) return 0;
        return businessMapper.insert(business);

    }
}
