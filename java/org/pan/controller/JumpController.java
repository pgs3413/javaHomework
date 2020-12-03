package org.pan.controller;

import org.pan.bean.Business;
import org.pan.bean.User;
import org.pan.service.AdminService;
import org.pan.service.BusinessService;
import org.pan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class JumpController {
    @Autowired
    AdminService adminService;
    @Autowired
    BusinessService businessService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin.do")
    public String adminLogin(){
        return "/adminLogin.html";
    }

    @RequestMapping("/adminLogin.do")
    public String adminLogin(String adminName, String password, HttpSession session){
        boolean b=adminService.adminLogin(adminName,password);
        if(b==true) {
            session.setAttribute("admin","true");
            return "redirect:/admin/busCheck.html";
        }else{
            session.setAttribute("admin","false");
            return "redirect:/adminLogin.html";
        }
    }
    @RequestMapping(value = "/adminCheck.do")
    @ResponseBody
    public String adminCheck(HttpSession session){
        Object obj=session.getAttribute("admin");
        if(obj==null) return "true";
        else if (((String)obj).equals("true")) return "true";
        else return "false";
    }
    @RequestMapping(value = "/businessLogin.do")
    public String businessLogin(String phone,String password,HttpSession session) {
        boolean b = businessService.businessLogin(phone, password);
        if (b == true) {
            long businessId = businessService.getIdByPhone(phone);
            session.setAttribute("business", (int) businessId);
            return "redirect:/business/busInfo.html";
        } else {
            session.setAttribute("business", 0);
            return "redirect:/bussLogin.html";
        }
    }
    @RequestMapping(value = "/businessCheck.do")
    @ResponseBody
    public String businessCheck(HttpSession session){
        Object obj=session.getAttribute("business");
        if(obj==null) return "true";
        else if ((int)obj==0) return "false";
        else return "true";
    }
    @RequestMapping(value = "/businessRegister.do")
    public String businessRegister(Business business,HttpSession session){
        business.setStatus(0);
        int result=businessService.addBusiness(business);
        if(result>0) {
            session.setAttribute("bussRegister","true");
            return "redirect:/bussLogin.html";
        }
        else return "/business/businessFail.html";
    }
    @RequestMapping(value = "/bussRegisterCheck.do")
    @ResponseBody
    public String bussRegisterCheck(HttpSession session){
        Object obj=session.getAttribute("bussRegister");
        if(obj==null) return "false";
        else return "true";
    }

    @RequestMapping(value = "/userLogin.do")
    public String userLogin(String stuNum,String password,HttpSession session){
        boolean b=userService.UserLogin(stuNum,password);
        if (b == true) {
            int userId=userService.getUserIdByStuNum(stuNum);
            session.setAttribute("user",userId);
            return "redirect:/user/person.html";
        } else {
            session.setAttribute("user",0);
            return "redirect:/login.html";
        }
    }
    @RequestMapping(value = "/userCheck.do")
    @ResponseBody
    public String userCheck(HttpSession session){
        Object obj=session.getAttribute("user");
        if(obj==null) return "true";
        else if ((int)obj==0) return "false";
        else return "true";
    }
    @RequestMapping(value = "/userRegister.do")
    public String userRegister(User user){
        int result=userService.addUser(user);
        if(result>0) {
            return "redirect:/login.html";
        }
        else return "/user/userFail.html";
    }
    @RequestMapping(value = "/torBar.do",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String torBar(HttpSession session){
        Object obj=session.getAttribute("user");
        if(obj==null) return "noBody";
        int userId=(int)obj;
        return userService.getUserNameById(userId);
    }
    @RequestMapping(value = "/joinToCartCheck.do")
    @ResponseBody
    public String joinToCartCheck(HttpSession session){
        Object obj=session.getAttribute("user");
        if(obj==null) return "false";
        else if ((int)obj==0) return "false";
        else return "true";
    }
}
