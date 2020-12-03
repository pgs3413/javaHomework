package org.pan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.pan.bean.Admin;
import org.pan.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource(type=AdminMapper.class)
    AdminMapper mapper;

    public boolean adminLogin(String adminName,String password){
        QueryWrapper<Admin> wrapper=new QueryWrapper<>();
        wrapper.eq("admin_name",adminName);
        wrapper.eq("password",password);
        int i=mapper.selectCount(wrapper);
        if(i>0) return true;
        return false;
    }
}
