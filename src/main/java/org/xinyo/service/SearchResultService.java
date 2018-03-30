package org.xinyo.service;

import org.xinyo.domain.SearchResult;

/**
 * Created by chengxinyong on 2018/3/30.
 */
public interface SearchResultService {
    SearchResult findByKeyword(String keyword);

    int add(SearchResult newResult);
}
