package com.system.TrendThreads.services;

import com.system.TrendThreads.entity.User;
import com.system.TrendThreads.pojo.UserPojo;

public interface UserService {
    void saveUser(UserPojo userPojo);
    User findByEmail(String email);

    User findUserById(Integer user_id);
}
