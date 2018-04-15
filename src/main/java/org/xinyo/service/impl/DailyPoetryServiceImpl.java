package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.DailyPoetryDao;
import org.xinyo.domain.DailyPoetry;
import org.xinyo.service.DailyPoetryService;

/**
 * Created by CHENG on 2018/4/15.
 */
@Service
public class DailyPoetryServiceImpl implements DailyPoetryService {

    @Autowired
    private DailyPoetryDao dailyPoetryDao;

    @Override
    public DailyPoetry selectById(String dayStr) {
        return dailyPoetryDao.selectById(dayStr);
    }
}
