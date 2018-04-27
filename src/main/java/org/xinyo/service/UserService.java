package org.xinyo.service;

import org.xinyo.domain.User;

/**
 * Created by chengxinyong on 2018/4/18.
 */
public interface UserService {
    boolean checkUsernameExist(String username);

    boolean checkEmailExist(String email);

    int addUser(User user);
}
