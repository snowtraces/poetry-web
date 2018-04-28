package org.xinyo.service;

import org.xinyo.domain.Poetry;

import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/3/27.
 */
public interface PoetryService {
    Poetry findPoetryById(Integer id);

    List<Poetry> findPoetryList();

    List<Poetry> findPoetryByKeyword(Map<String, Object> params);

    int countTotalPoetryByKeyword(Map<String, Object> params);

    List<Integer> findTop100IdByKeyword(String keyword);

    List<Poetry> findTrByIds(List<String> idList);

    List<Poetry> findSpByIds(List<String> idList);

    Poetry findByIdAndLanguage(Map<String, Object> params);

    List<Poetry> findByKeywordAndLanguage(Map<String, Object> params);

    void updateKeywordsById(Map<String, Object> params);

    void updateParagrahsSpById(Map<String, Object> i);

    int updateTagsById(Map<String, Object> params);

    List<Poetry> find1000ById(int i);
}
