package org.xinyo.service;

import org.xinyo.domain.DailyPoetry;

/**
 * Created by CHENG on 2018/4/15.
 */
public interface DailyPoetryService {
    DailyPoetry selectById(String dayStr);
}
