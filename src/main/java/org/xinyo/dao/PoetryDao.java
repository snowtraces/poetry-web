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

    List<Poetry> findPoetryTrByKeyword(Map<String, Object> params);

    List<Poetry> findPoetrySpByKeyword(Map<String, Object> params);

    int countTotalPoetryByKeyword(Map<String, Object> params);

    List<Integer> findTop100IdByKeyword(String keyword);

    List<Poetry> findTrByIds(List<String> idList);

    List<Poetry> findSpByIds(List<String> idList);

    Poetry findByIdAndLanguage(Map<String, Object> params);

    List<Poetry> findByKeywordAndLanguage(Map<String, Object> params);
}
