package org.pan.controller;

import org.pan.bean.Goods;
import org.pan.bean.Order;
import org.pan.bean.User;
import org.pan.mapper.GoodsMapper;
import org.pan.service.CartService;
import org.pan.service.GoodsService;
import org.pan.service.TradeService;
import org.pan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    TradeService tradeService;
    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/person.do")
    @ResponseBody
    public User person(HttpSession session){
        Object obj=session.getAttribute("user");
        if(obj==null) return null;
        int userId=(int)obj;
        return userService.getUserById(userId);
    }
    @RequestMapping(value = "/userExit.do")
    public String userExit(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index.html";
    }
    @RequestMapping(value = "/joinToCart.do")
    @ResponseBody
    public int joinToCart(int goodsId,HttpSession session){
        int userId=(int)session.getAttribute("user");
        cartService.joinToCart(goodsId,userId);
        return cartService.getCartNumByUserId(userId);
    }
    @RequestMapping(value = "/getCartNum.do")
    @ResponseBody
    public int getCartNum(HttpSession session){
        int userId=(int)session.getAttribute("user");
        return cartService.getCartNumByUserId(userId);
    }
    @RequestMapping(value = "/order.do")
    @ResponseBody
    public List<Order> order(HttpSession session){
        int userId=(int)session.getAttribute("user");
        return tradeService.getUserOrderById(userId);

    }
    @RequestMapping(value = "/shopCar.do")
    @ResponseBody
    public List<Goods> shopCar(HttpSession session){
        int userId=(int)session.getAttribute("user");
        return cartService.getCartGoodsByUserId(userId);
    }
    @RequestMapping(value = "/btnCheck.do")
    @ResponseBody
    public String btnCheck(int goodsId,HttpSession session){
        int userId=(int)session.getAttribute("user");
        boolean b=cartService.cartCheck(userId,goodsId);
        if(b==true) return "true";
        else return "false";
    }
    @RequestMapping(value = "/cancelCart.do")
    public String cancelCart(int id,HttpSession session){
        int userId=(int)session.getAttribute("user");
        cartService.cancelCart(userId,id);
        return "redirect:/user/shopCar.html";
    }
    @RequestMapping(value = "/goodsCheck.do")
    @ResponseBody
    public String goodsCheck(int goodsId){
        boolean b=goodsService.checkGoodsStatus(goodsId);
        if(b==true) return "true";
        return "false";
    }
    @RequestMapping(value = "/pay.do")
    @ResponseBody
    public String pay(int goodsId,String address,HttpSession session){
        int userId=(int)session.getAttribute("user");
        boolean b=tradeService.addTrade(goodsId,userId,address);
        if(b==true) return "true";
        else return "false";
    }
}
