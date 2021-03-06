package org.xinyo.service;

import org.xinyo.domain.FullPoetry;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.SearchResult;

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

    Poetry findByAuthorAndPoetryBegin(Map<String,String> param);

    List<Poetry> adminListByKeyword(Map<String, Object> params);

    List<Poetry> adminListByIds(List<String> idList);

    int adminCountByKeyword(Map<String,Object> params);

    FullPoetry findFullPoetryById(Integer id);

    Map<String,Object> editPoetry(FullPoetry fullPoetry);
}
