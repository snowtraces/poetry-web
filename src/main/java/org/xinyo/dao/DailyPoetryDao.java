package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.DailyPoetry;

/**
 * Created by CHENG on 2018/4/15.
 */
@Repository
public interface DailyPoetryDao {
    DailyPoetry selectById(String day);
}
