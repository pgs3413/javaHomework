package org.pan.service;

import org.pan.bean.User;

public interface UserService {
    boolean UserLogin(String stuNum,String password);
    int getUserIdByStuNum(String stuNum);
    int addUser(User user);
    User getUserById(int id);
    String getUserNameById(int id);

}
