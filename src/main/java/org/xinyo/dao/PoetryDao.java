package org.xinyo.dao;

import org.springframework.stereotype.Repository;
import org.xinyo.domain.Poetry;

import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Repository
public interface PoetryDao {
    Poetry findById(Integer poetryId);

    List<Poetry> findPoetryList();

    List<Poetry> findPoetryByKeyword(Map<String, Object> params);
}
