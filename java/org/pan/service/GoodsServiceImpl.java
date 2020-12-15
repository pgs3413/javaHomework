package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.Business;
import org.pan.bean.Goods;
import org.pan.bean.GoodsAndBusiness;
import org.pan.bean.Recommend;
import org.pan.mapper.BusinessMapper;
import org.pan.mapper.GoodsMapper;
import org.pan.mapper.OwnMapper;
import org.pan.mapper.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BusinessMapper businessMapper;
    @Autowired
    RecommendMapper recommendMapper;
    @Autowired
    OwnMapper ownMapper;

    @Override
    public List<GoodsAndBusiness> getGoodsList(int status) {
        List<GoodsAndBusiness> list=new ArrayList<GoodsAndBusiness>();
        QueryWrapper<Goods> wrapper=new QueryWrapper<Goods>();
        wrapper.eq("status",status);
        List<Goods> goodsList=goodsMapper.selectList(wrapper);
        for(Goods goods:goodsList){
            Business business=businessMapper.selectById(goods.getBusinessId());
            GoodsAndBusiness goodsAndBusiness=new GoodsAndBusiness();
            goodsAndBusiness.setBusiness(business);
            goodsAndBusiness.setGoods(goods);
            if(status==1){
                QueryWrapper<Recommend> recommendQueryWrapper=new QueryWrapper<>();
                recommendQueryWrapper.eq("goods_id",goods.getId());
               int result=recommendMapper.selectCount(recommendQueryWrapper);
               if(result>0){
                   goodsAndBusiness.setIsRecommend("true");
               }
            }
            list.add(goodsAndBusiness);
        }
        return list;
    }

    @Override
    public int goodsPass(int id) {
        Goods goods=new Goods();
        goods.setId(id);
        goods.setStatus(1);
        return goodsMapper.updateById(goods);

    }

    @Override
    public int goodsFail(int id) {
        Goods goods=new Goods();
        goods.setId(id);
        goods.setStatus(-1);
        return goodsMapper.updateById(goods);
    }

    @Override
    public int setGoodsRecommend(int goodsId) {
        Recommend recommend=new Recommend();
        recommend.setGoodsId(goodsId);
        return recommendMapper.insert(recommend);

    }

    @Override
    public int cancelGoodsRecommend(int goodsId) {
        QueryWrapper<Recommend> wrapper=new QueryWrapper<Recommend>();
        wrapper.eq("goods_id",goodsId);
        return recommendMapper.delete(wrapper);

    }

    @Override
    public List<GoodsAndBusiness> getAllGoodsAndBusiness() {
        List<GoodsAndBusiness> list=new ArrayList<>();
        List<Goods> goodsList=goodsMapper.selectList(null);
        for(Goods goods:goodsList){
            Business business=businessMapper.selectById(goods.getBusinessId());
            GoodsAndBusiness goodsAndBusiness=new GoodsAndBusiness();
            goodsAndBusiness.setBusiness(business);
            goodsAndBusiness.setGoods(goods);
            list.add(goodsAndBusiness);
            }
        return list;
    }

    @Override
    public List<Goods> getGoodsByBusinessId(int businessId) {
       QueryWrapper<Goods> wrapper=new QueryWrapper<>();
       wrapper.eq("business_id",businessId);
        return goodsMapper.selectList(wrapper);
    }

    @Override
    public int addGoods(String title, String details, String type, int price, int businessId, int status, String photo) {
        Goods goods=new Goods();
        goods.setTitle(title);
        goods.setDetails(details);
        goods.setType(type);
        goods.setPrice(price);
        goods.setBusinessId(businessId);
        goods.setStatus(status);
        goods.setPhoto(photo);
        goods.setId(null);
        return goodsMapper.insert(goods);
    }

    @Override
    public Goods getGoodsById(int goodsId) {
        return goodsMapper.selectById(goodsId);
    }

    @Override
    public GoodsAndBusiness getGoodsAndBusinessByGoodsId(int goodsId) {
        GoodsAndBusiness goodsAndBusiness=new GoodsAndBusiness();
        Goods goods=goodsMapper.selectById(goodsId);
        Business business=businessMapper.selectById(goods.getBusinessId());
        goodsAndBusiness.setGoods(goods);
        goodsAndBusiness.setBusiness(business);
        return goodsAndBusiness;
    }

    @Override
    public List<Goods> getRecommendGoods() {
        List<Recommend> recommendList=ownMapper.getRecommendLimit(4);
        List<Goods> goodsList=new ArrayList<>();
        for(Recommend recommend:recommendList){
            Goods goods=goodsMapper.selectById(recommend.getGoodsId());
            goodsList.add(goods);
        }
        return goodsList;

    }

    @Override
    public List<Goods> getGoodsIndex() {
        return ownMapper.getGoodsLimit(8);
    }

    @Override
    public List<Goods> getAllGoodsByType(int typeNum) {
        String type=null;
        if(typeNum==0) {
            List<Recommend> recommendList=recommendMapper.selectList(null);
            List<Goods> goodsList=new ArrayList<>();
            for(Recommend recommend:recommendList){
                Goods goods=goodsMapper.selectById(recommend.getGoodsId());
                goodsList.add(goods);
            }
            return goodsList;
        }
        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq("status",1);
        if(typeNum==1) return goodsMapper.selectList(wrapper);
        if(typeNum==2) type="宿舍用品";
        if(typeNum==3) type="电子设备";
        if(typeNum==4) type="书籍";
        if(typeNum==5) type="其他";
        wrapper.eq("type",type);
        return goodsMapper.selectList(wrapper);
    }

    @Override
    public List<Goods> getGoodsViaSearch(String s) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("status",1);
        map.put("title","%"+s+"%");
        map.put("details","%"+s+"%");
        return ownMapper.getGoodsViaSearch(map);

    }

    @Override
    public boolean checkGoodsStatus(int goodsId) {
        Goods goods=goodsMapper.selectById(goodsId);
        if(goods.getStatus()==2) return true;
        return false;
    }


}
