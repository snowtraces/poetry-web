package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.SearchResult;

/**
 * Created by chengxinyong on 2018/3/30.
 */
@Repository
public interface SearchResultDao {
    SearchResult findByKeyword(String keyword);

    int insert(SearchResult newResult);
}
