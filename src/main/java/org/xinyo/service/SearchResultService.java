package org.xinyo.service;

import org.xinyo.domain.SearchResult;

import java.util.Map;

/**
 * Created by chengxinyong on 2018/3/30.
 */
public interface SearchResultService {
    SearchResult findByKeyword(String keyword);

    int add(SearchResult newResult);

    void addNewSearchResult(String keyword, int total);

    Map<String, Object>  searchByKeyword(Map<String, Object> params);
}
