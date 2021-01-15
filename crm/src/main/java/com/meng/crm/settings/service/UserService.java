package com.meng.crm.settings.service;

import com.meng.crm.settings.domain.Users;
import com.meng.crm.exception.LoginException;

import java.util.List;

public interface UserService {
    Users login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<Users> getUserList();
}
