package org.pan.mapper;

import org.pan.bean.Goods;
import org.pan.bean.Recommend;

import java.util.List;

public interface OwnMapper {
    List<Goods> getGoodsLimit(int num);
    List<Recommend> getRecommendLimit(int num);
}
