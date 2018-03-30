package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.PoetryDao;
import org.xinyo.domain.Poetry;
import org.xinyo.service.PoetryService;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Poetry> findPoetryByKeyword(Map<String, Object> params) {
        return poetryDao.findPoetryByKeyword(params);
    }

    @Override
    public Poetry findPoetryTrById(Integer id) {
        return poetryDao.findTrById(id);
    }

    @Override
    public Poetry findPoetrySpById(Integer id) {
        return poetryDao.findSpById(id);
    }

    @Override
    public List<Poetry> findPoetryTrByKeyword(Map<String, Object> params) {
        return poetryDao.findPoetryTrByKeyword(params);
    }

    @Override
    public List<Poetry> findPoetrySpByKeyword(Map<String, Object> params) {
        return poetryDao.findPoetrySpByKeyword(params);
    }

    @Override
    public long countTotalPoetryByKeyword(Map<String, Object> params) {
        return poetryDao.countTotalPoetryByKeyword(params);
    }
}
