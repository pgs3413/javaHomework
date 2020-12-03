package org.pan.controller;

import org.pan.bean.Goods;
import org.pan.bean.GoodsAndBusiness;
import org.pan.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;


@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/details.do")
    public String details(){
        return "/goods.html";
    }

    @RequestMapping(value = "/goodsContent.do")
    @ResponseBody
    public GoodsAndBusiness goodsContent(String goodsId){
        if(goodsId==null) return null;
        int id= Integer.parseInt(goodsId);
        return goodsService.getGoodsAndBusinessByGoodsId(id);
    }

    @RequestMapping(value = "/recommendGoods.do")
    @ResponseBody
    public List<Goods> recommendGoods(){
        return goodsService.getRecommendGoods();
    }
    @RequestMapping(value = "/goodsIndex.do")
    @ResponseBody
    public List<Goods> goodsIndex(){
        return goodsService.getGoodsIndex();
    }

    @RequestMapping(value = "/sort.do")
    public String sort(){
        return "/show.html";
    }

    @RequestMapping(value = "/goodsSort.do")
    @ResponseBody
    public List<Goods> goodsSort(int type){
        return goodsService.getAllGoodsByType(type);
    }

    @RequestMapping(value = "/search.do")
    public String search(){
        return "/search.html";
    }

    @RequestMapping(value = "goodsSearch.do")
    @ResponseBody
    public List<Goods> goodsSearch(String s){
        String search=null;
        try {
            search=URLDecoder.decode(s,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goodsService.getGoodsViaSearch(search);
    }
}
