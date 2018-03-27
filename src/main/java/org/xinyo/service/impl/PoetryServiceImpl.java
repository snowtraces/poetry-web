package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.PoetryDao;
import org.xinyo.domain.Poetry;
import org.xinyo.service.PoetryService;

import java.util.List;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Service
public class PoetryServiceImpl implements PoetryService {
    @Autowired
    private PoetryDao poetryDao;

    @Override
    public Poetry findPoetryById(Integer id) {
        return poetryDao.findById(id);
    }

    @Override
    public List<Poetry> findPoetryList() {
        return poetryDao.findPoetryList();
    }
}
