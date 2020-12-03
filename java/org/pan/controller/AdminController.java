package org.pan.controller;

import org.pan.bean.Business;
import org.pan.bean.Goods;
import org.pan.bean.GoodsAndBusiness;
import org.pan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    BusinessService businessService;
    @Autowired
    GoodsService goodsService;


    @RequestMapping(value = "/busCheck.do")
    @ResponseBody
    public List<Business> busCheck(){
        return businessService.busCheck();

    }
    @RequestMapping(value = "/busOperate.do")
    public String busOperate(int op,int id){
        int flag=0;
        if(op==1){
        flag=businessService.busPass(id);
        }else if(op==2){
        flag=businessService.busFail(id);
        }
        if(flag==0) return "/admin/adminFail.html";
        return "redirect:/admin/busCheck.html";
    }
    @RequestMapping(value = "/goodsCheck.do")
    @ResponseBody
    public List<GoodsAndBusiness> goodsCheck(){
        return goodsService.getGoodsList(0);
    }
    @RequestMapping(value = "/goodsOperate.do")
    public String goodsOperate(int op,int id){
        int flag=0;
        if(op==1){
            flag=goodsService.goodsPass(id);
        }else if(op==2){
            flag=goodsService.goodsFail(id);
        }
        if(flag==0) return "/admin/adminFail.html";
        return "redirect:/admin/goodsCheck.html";
    }
    @RequestMapping(value = "/recommend.do")
    @ResponseBody
    public List<GoodsAndBusiness> recommend(){
        return goodsService.getGoodsList(1);
    }
    @RequestMapping(value = "/recommendOperate.do")
    public String recommendOperate(int op,int id){
        int flag=0;
        if(op==1){
            flag=goodsService.setGoodsRecommend(id);
        }else if(op==2){
            flag=goodsService.cancelGoodsRecommend(id);
        }
        if(flag==0) return "/admin/adminFail.html";
        return "redirect:/admin/recommendManage.html";
    }
    @RequestMapping(value = "/goodsList.do")
    @ResponseBody
    public List<GoodsAndBusiness> goodsList(){

        return goodsService.getAllGoodsAndBusiness();
    }
}
