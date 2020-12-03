package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.Goods;
import org.pan.bean.Trade;
import org.pan.bean.User;
import org.pan.mapper.GoodsMapper;
import org.pan.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;



    @Override
    public boolean UserLogin(String stuNum, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("stu_num",stuNum);
        wrapper.eq("password",password);
        int result=userMapper.selectCount(wrapper);
        if(result>0) return true;
        return false;
    }

    @Override
    public int getUserIdByStuNum(String stuNum) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("stu_num",stuNum);
        User user=userMapper.selectOne(wrapper);
        return user.getId();
    }

    @Override
    public int addUser(User user) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("stu_num",user.getStuNum());
        int result=userMapper.selectCount(wrapper);
        if(result>0) return 0;
        return userMapper.insert(user);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public String getUserNameById(int id) {
        User user=userMapper.selectById(id);
        return user.getUserName();
    }


}
