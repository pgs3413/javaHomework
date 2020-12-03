package org.pan.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.pan.bean.Business;
import org.pan.bean.Goods;
import org.pan.bean.Order;
import org.pan.service.BusinessService;
import org.pan.service.GoodsService;
import org.pan.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/business")
public class BusinessController {
    @Autowired
    BusinessService businessService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    TradeService tradeService;

    @RequestMapping(value = "/busInfo.do")
    @ResponseBody
    public Business busInfo(HttpSession session){
        Object obj=session.getAttribute("business");
        if(obj==null) return null;
        int businessId=(int)obj;
        return  businessService.getBusinessById(businessId);
    }
    @RequestMapping(value = "/businessExit.do")
    public String businessExit(HttpSession session){
        session.removeAttribute("business");
        return "redirect:/bussLogin.html";
    }
    @RequestMapping(value = "/goodsList.do")
    @ResponseBody
    public List<Goods> goodsList(HttpSession session){
        Object obj=session.getAttribute("business");
        if(obj==null) return null;
        int businessId=(int)obj;
        return goodsService.getGoodsByBusinessId(businessId);

    }
    @RequestMapping(value = "/orderList.do")
    @ResponseBody
    public List<Order> orderList(HttpSession session){
        Object obj=session.getAttribute("business");
        if(obj==null) return null;
        int businessId=(int)obj;
        return businessService.getOrder(businessId);

    }
    @RequestMapping(value = "/orderFinish.do")
    public String orderFinish(int id){
        int result=tradeService.changeStatusById(id);
        if(result>0) return "redirect:/business/goodslist.html";
        else return "/business/businessFail.html";
    }
    @RequestMapping(value = "/addGoods.do",produces = {"text/plain;charset=utf-8"})
    @ResponseBody
    public String addGoods(HttpServletRequest request){
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        String title=null,details=null,price=null,type=null;
        String fileName=null;
        try{
            List<FileItem> list=upload.parseRequest(request);
            for(FileItem item:list){
                if(item.isFormField()){
                    String fieldName=item.getFieldName();
                    if(fieldName.equals("title")){
                        title=item.getString("utf-8");
                    }
                    if(fieldName.equals("details")){
                        details=item.getString("utf-8");
                    }
                    if(fieldName.equals("price")){
                        price=item.getString("utf-8");
                    }
                    if(fieldName.equals("type")){
                        type=item.getString("utf-8");
                    }
                }else{
                    String imagesPath=request.getSession().getServletContext().getRealPath("/images");
                    long time=System.currentTimeMillis();
                    fileName=time+".jpg";
                    String path=imagesPath+"/"+fileName;
                    InputStream in=item.getInputStream();
                    FileOutputStream out=new FileOutputStream(path);
                    int len=0;
                    byte[] buf=new byte[1024];
                    while((len=in.read(buf))!=-1){
                        out.write(buf,0,len);
                    }
                    in.close();
                    out.close();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Object obj=request.getSession().getAttribute("business");
        if(obj==null) return "操作失败";
        int businessId=(int)obj;
        int result=goodsService.addGoods(title,details,type,Integer.parseInt(price),businessId,0,fileName);
        if(result>0) return "商品提交成功！请等待管理员审核";
        else return "操作失败";

    }
    @RequestMapping(value = "/test.do")
    @ResponseBody
    public String test(HttpServletRequest request){
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        String msg=null;
        try {
            List<FileItem> list=upload.parseRequest(request);
            for(FileItem item:list){
                if(item.isFormField()){
                    String fieldName=item.getFieldName();
                    msg=item.getString();
                    System.out.println(msg);
                }else{
                    String fileName=item.getName();
                    System.out.println(fileName);
                    InputStream in=item.getInputStream();
                    String imagesPath=request.getSession().getServletContext().getRealPath("/images");
                    System.out.println(imagesPath);
                    String path=imagesPath+"/"+fileName;
                    FileOutputStream out=new FileOutputStream(path);
                    int len=0;
                    byte[] buf=new byte[1024];
                    while((len=in.read(buf))!=-1){
                        out.write(buf,0,len);
                    }
                    in.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(msg);
        return "我已收到！";
    }


}
