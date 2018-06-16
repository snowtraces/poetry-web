package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.Author;

import java.util.Map;

/**
 * Created by CHENG on 2018/3/31.
 */
@Repository
public interface AuthorDao {
    Author findByIdAndLanguage(Map<String, Object> params);

    void updateDescSpById(Map<String, Object> params);

    Author findByAuthorAndLanguage(Map<String,Object> authorParam);
}
