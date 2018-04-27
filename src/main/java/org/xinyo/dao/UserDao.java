package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.User;

/**
 * Created by chengxinyong on 2018/4/18.
 */
@Repository
public interface UserDao {
    User selectByUsername(String username);

    User selectByEmail(String email);

    int insert(User user);
}
