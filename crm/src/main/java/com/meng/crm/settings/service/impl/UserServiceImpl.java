package com.meng.crm.settings.service.impl;

import com.meng.crm.settings.dao.UserDao;
import com.meng.crm.settings.domain.Users;
import com.meng.crm.exception.LoginException;
import com.meng.crm.settings.service.UserService;
import com.meng.crm.utils.DateTimeUtil;
import com.meng.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userdao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public Users login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map=new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        Users user=userdao.login(map);
        if (user==null){
            throw new LoginException("账号密码错误");
        }

        String expiretime=user.getExpireTime();
        String systime=DateTimeUtil.getSysTime();
        if (expiretime.compareTo(systime)<0){
            throw new LoginException("账号已过期");
        }

        String lockstate=user.getLockState();
        if ("0".equals(lockstate)){
            throw new LoginException("账号已锁定");
        }

        String allowIps=user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址被限制");
        }


        return user;
    }

    @Override
    public List<Users> getUserList() {
        List<Users> uList=userdao.getUserList();
        return uList;
    }


}
