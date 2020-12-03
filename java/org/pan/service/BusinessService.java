package org.pan.service;

import org.pan.bean.Business;
import org.pan.bean.Order;

import java.util.List;

public interface BusinessService {
    List<Business> busCheck();
    int busPass(int id);
    int busFail(int id);
    boolean businessLogin(String phone,String password);
    long getIdByPhone(String phone);
    Business getBusinessById(int id);
    List<Order> getOrder(int businessId);
    int addBusiness(Business business);
}
