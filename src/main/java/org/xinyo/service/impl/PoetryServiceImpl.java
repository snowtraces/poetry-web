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
    public int countTotalPoetryByKeyword(Map<String, Object> params) {
        return poetryDao.countTotalPoetryByKeyword(params);
    }

    @Override
    public List<Integer> findTop100IdByKeyword(String keyword) {
        return poetryDao.findTop100IdByKeyword(keyword);
    }

    @Override
    public List<Poetry> findTrByIds(List<String> idList) {
        return poetryDao.findTrByIds(idList);
    }
    @Override
    public List<Poetry> findSpByIds(List<String> idList) {
        return poetryDao.findSpByIds(idList);
    }

    @Override
    public Poetry findByIdAndLanguage(Map<String, Object> params) {
        return poetryDao.findByIdAndLanguage(params);
    }

    @Override
    public List<Poetry> findByKeywordAndLanguage(Map<String, Object> params) {
        return poetryDao.findByKeywordAndLanguage(params);
    }

    @Override
    public void updateKeywordsById(Map<String, Object> params) {
        poetryDao.updateKeywordsById(params);
    }
}
