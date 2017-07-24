package com.havetogg.ssm.service.impl;

import com.havetogg.ssm.dao.UserDao;
import com.havetogg.ssm.model.User;
import com.havetogg.ssm.result.PageResult;
import com.havetogg.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;

    public User getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public PageResult<User> get(int pageNum, int pageSize) {
        if(pageSize>1000){
            return null;
        }
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setPageSize(pageSize);
        pageResult.setPageNum(pageNum);
        User user = new User();
        user.pageNum = pageNum;
        user.pageSize = pageSize;
        int count = userDao.getCount();
        pageResult.setTotalCount(count);
        List<User> users = userDao.getPageByCondition(user);
        pageResult.setDatas(users);
        return pageResult;
    }

    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone,state);
    }
    
    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }
}
