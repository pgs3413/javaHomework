package org.pan.service;

import org.pan.bean.Order;
import org.pan.bean.Trade;

import java.util.List;

public interface TradeService {
    int changeStatusById(int id);
    List<Trade> getTradesByUserId(int userId);
    List<Order> getUserOrderById(int userId);
    boolean addTrade(int goodsId,int userId,String address);
}
