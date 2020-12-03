package org.pan.service;

import org.pan.bean.Goods;
import org.pan.bean.GoodsAndBusiness;

import java.util.List;

public interface GoodsService {
    List<GoodsAndBusiness> getGoodsList(int status);
    int goodsPass(int id);
    int goodsFail(int id);
    int setGoodsRecommend(int goodsId);
    int cancelGoodsRecommend(int goodsId);
    List<GoodsAndBusiness> getAllGoodsAndBusiness();
    List<Goods> getGoodsByBusinessId(int businessId);
    int addGoods(String title,String details,String type,int price,int businessId,int status,String photo);
    Goods getGoodsById(int goodsId);
    GoodsAndBusiness getGoodsAndBusinessByGoodsId(int goodsId);
    List<Goods> getRecommendGoods();
    List<Goods> getGoodsIndex();
    List<Goods> getAllGoodsByType(int typeNum);
    List<Goods> getGoodsViaSearch(String s);
    boolean checkGoodsStatus(int goodsId);

}
