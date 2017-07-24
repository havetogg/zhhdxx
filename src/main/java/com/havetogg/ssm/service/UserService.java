package com.havetogg.ssm.service;

import com.havetogg.ssm.model.User;
import com.havetogg.ssm.result.PageResult;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User getUserById(Long userId);

    PageResult<User> get(int pageNum,int pageSize);
}
