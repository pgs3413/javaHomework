package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.Cart;
import org.pan.bean.Goods;
import org.pan.mapper.CartMapper;
import org.pan.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int joinToCart(int goodsId, int userId) {
        Cart cart=new Cart();
        cart.setUserId(userId);
        cart.setGoodsId(goodsId);
        return cartMapper.insert(cart);
    }

    @Override
    public int getCartNumByUserId(int userid) {
        QueryWrapper<Cart> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userid);
        return cartMapper.selectCount(wrapper);
    }

    @Override
    public List<Goods> getCartGoodsByUserId(int userId) {
        QueryWrapper<Cart> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Cart> cartList=cartMapper.selectList(wrapper);
        List<Goods> goodsList=new ArrayList<>();
        for(Cart cart:cartList){
            Goods goods=goodsMapper.selectById(cart.getGoodsId());
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public boolean cartCheck(int userId, int goodsId) {
        QueryWrapper<Cart> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("goods_id",goodsId);
        int result=cartMapper.selectCount(wrapper);
        if(result>0) return true;
        else return false;
    }

    @Override
    public int cancelCart(int userId, int goodsId) {
        QueryWrapper<Cart> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("goods_id",goodsId);
        return cartMapper.delete(wrapper);

    }
}
