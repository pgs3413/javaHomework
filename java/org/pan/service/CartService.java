package org.pan.service;

import org.pan.bean.Goods;

import java.util.List;

public interface CartService {
    int joinToCart(int goodsId,int userId);
    int getCartNumByUserId(int userid);
    List<Goods> getCartGoodsByUserId(int userId);
    boolean cartCheck(int userId,int goodsId);
    int cancelCart(int userId,int goodsId);
}
