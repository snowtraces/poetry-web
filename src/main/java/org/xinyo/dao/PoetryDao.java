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

    int countTotalPoetryByKeyword(Map<String, Object> params);

    List<Integer> findTop100IdByKeyword(String keyword);

    List<Poetry> findTrByIds(List<String> idList);

    List<Poetry> findSpByIds(List<String> idList);

    Poetry findByIdAndLanguage(Map<String, Object> params);

    List<Poetry> findByKeywordAndLanguage(Map<String, Object> params);

    int updateKeywordsById(Map<String, Object> params);

    void updateParagrahsSpById(Map<String, Object> params);

    int countTotalPoetryByAuthor(Map<String, Object> params);

    List<Poetry> findByAuthorAndLanguage(Map<String, Object> params);

    List<Integer> findTop100IdByAuthor(String replace);

    int countTotalPoetryByTag(Map<String, Object> newParams);

    List<Poetry> findByTagAndLanguage(Map<String, Object> newParams);

    List<Integer> findTop100IdByTag(String replace);

    int updateTagsById(Map<String, Object> params);

    List<Poetry> find1000ById(int i);
}
