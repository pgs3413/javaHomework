package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.*;
import org.pan.mapper.CartMapper;
import org.pan.mapper.GoodsMapper;
import org.pan.mapper.RecommendMapper;
import org.pan.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    CartMapper cartMapper;

    @Override
    public int changeStatusById(int id) {
        Trade trade=new Trade();
        trade.setId(id);
        trade.setStatus(1);
        return tradeMapper.updateById(trade);

    }

    @Override
    public List<Trade> getTradesByUserId(int userId) {
        QueryWrapper<Trade> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return tradeMapper.selectList(wrapper);
    }

    @Override
    public List<Order> getUserOrderById(int userId) {
        List<Trade> tradeList=getTradesByUserId(userId);
        List<Order> orderList=new ArrayList<>();
        for(Trade trade:tradeList){
            Order order=new Order();
            Goods goods=goodsMapper.selectById(trade.getGoodsId());
            order.setGoods(goods);
            order.setTrade(trade);
            orderList.add(order);
        }
        return orderList;
    }
    @Override
    public boolean addTrade(int goodsId, int userId, String address) {
        Trade trade=new Trade();
        Goods goods=goodsMapper.selectById(goodsId);
        trade.setBusinessId(goods.getBusinessId());
        trade.setGoodsId(goodsId);
        trade.setUserAddress(address);
        trade.setUserId(userId);
        trade.setStatus(0);
        int result=tradeMapper.insert(trade);
        Goods goods1=new Goods();
        goods1.setId(goodsId);
        goods1.setStatus(2);
        int result1=goodsMapper.updateById(goods1);
        QueryWrapper<Recommend> recommendQueryWrapper=new QueryWrapper<>();
        recommendQueryWrapper.eq("goods_id",goodsId);
        recommendMapper.delete(recommendQueryWrapper);
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",userId);
        cartQueryWrapper.eq("goods_id",goodsId);
        cartMapper.delete(cartQueryWrapper);
        if(result>0&&result1>0) return true;
        else return false;
    }

}
