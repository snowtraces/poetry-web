package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.SearchResultDao;
import org.xinyo.domain.SearchResult;
import org.xinyo.service.SearchResultService;

/**
 * Created by chengxinyong on 2018/3/30.
 */
@Service
public class SearchResultServiceImpl implements SearchResultService{
    @Autowired
    private SearchResultDao searchResultDao;

    @Override
    public SearchResult findByKeyword(String keyword) {
        return searchResultDao.findByKeyword(keyword);
    }

    @Override
    public int add(SearchResult newResult) {
        return searchResultDao.insert(newResult);
    }
}
