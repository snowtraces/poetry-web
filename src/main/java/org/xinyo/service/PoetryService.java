package org.xinyo.service;

import org.xinyo.domain.Poetry;

import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/3/27.
 */
public interface PoetryService {
    Poetry findPoetryById(Integer poetryId);

    List<Poetry> findPoetryList();

    List<Poetry> findPoetryByKeyword(Map<String, Object> params);

    Poetry findPoetryTrById(Integer id);

    Poetry findPoetrySpById(Integer id);

    List<Poetry> findPoetryTrByKeyword(Map<String, Object> params);

    List<Poetry> findPoetrySpByKeyword(Map<String, Object> params);

    long countTotalPoetryByKeyword(Map<String, Object> params);

    List<Integer> findTop100IdByKeyword(String keyword);

    List<Poetry> findTrByIds(List<String> idList);

    List<Poetry> findSpByIds(List<String> idList);
}
