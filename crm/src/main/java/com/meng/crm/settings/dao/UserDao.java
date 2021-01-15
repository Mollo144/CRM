package com.meng.crm.settings.dao;

import com.meng.crm.settings.domain.Users;

import java.util.List;
import java.util.Map;

public interface UserDao {

    Users login(Map<String, String> map);

    List<Users> getUserList();
}
