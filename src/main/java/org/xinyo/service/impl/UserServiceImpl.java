package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.UserDao;
import org.xinyo.domain.User;
import org.xinyo.service.UserService;

/**
 * Created by chengxinyong on 2018/4/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkUsernameExist(String username) {
        User user = userDao.selectByUsername(username);
        return user == null ? false : true;
    }

    @Override
    public boolean checkEmailExist(String email) {
        User user = userDao.selectByEmail(email);
        return user == null ? false : true;
    }

    @Override
    public int addUser(User user) {
        return userDao.insert(user);
    }

    @Override
    public User validateUser(User userParam) {
        return userDao.selectByParam(userParam);
    }
}
