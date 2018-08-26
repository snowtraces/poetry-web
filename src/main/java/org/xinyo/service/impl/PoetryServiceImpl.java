package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.PoetryDao;
import org.xinyo.domain.FullPoetry;
import org.xinyo.domain.Poetry;
import org.xinyo.service.PoetryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Service
public class PoetryServiceImpl implements PoetryService {
    @Autowired
    private PoetryDao poetryDao;

    @Override
    public Poetry findPoetryById(Integer id) {
        return poetryDao.findById(id);
    }

    @Override
    public List<Poetry> findPoetryList() {
        return poetryDao.findPoetryList();
    }

    @Override
    public List<Poetry> findPoetryByKeyword(Map<String, Object> params) {
        return poetryDao.findPoetryByKeyword(params);
    }

    @Override
    public int countTotalPoetryByKeyword(Map<String, Object> params) {
        String keyword = (String) params.get("keyword");
        if(keyword != null){
            if(keyword.startsWith("author:")){
                Map<String, Object> newParams = copyMap(params);
                newParams.put("keyword", keyword.replace("author:",""));
                return poetryDao.countTotalPoetryByAuthor(newParams);
            } else if (keyword.startsWith("tag:")) {
                Map<String, Object> newParams = copyMap(params);
                newParams.put("keyword", keyword.replace("tag:",""));
                return poetryDao.countTotalPoetryByTag(newParams);
            }
        }
        return poetryDao.countTotalPoetryByKeyword(params);
    }

    @Override
    public int adminCountByKeyword(Map<String, Object> params) {
        return poetryDao.adminCountByKeyword(params);
    }

    @Override
    public List<Integer> findTop100IdByKeyword(String keyword) {
        if(keyword != null){
            if(keyword.startsWith("author:")){
                return poetryDao.findTop100IdByAuthor(keyword.replace("author:",""));
            } else if (keyword.startsWith("tag:")) {
                return poetryDao.findTop100IdByTag(keyword.replace("tag:",""));
            }

        }
        return poetryDao.findTop100IdByKeyword(keyword);
    }

    @Override
    public List<Poetry> findTrByIds(List<String> idList) {
        return poetryDao.findTrByIds(idList);
    }
    @Override
    public List<Poetry> findSpByIds(List<String> idList) {
        return poetryDao.findSpByIds(idList);
    }
    @Override
    public List<Poetry> adminListByIds(List<String> idList) {
        return poetryDao.adminListByIds(idList);
    }

    @Override
    public Poetry findByIdAndLanguage(Map<String, Object> params) {
        return poetryDao.findByIdAndLanguage(params);
    }
    @Override
    public FullPoetry findFullPoetryById(Integer id) {
        return poetryDao.findFullPoetryById(id);
    }

    @Override
    public List<Poetry> findByKeywordAndLanguage(Map<String, Object> params) {
        String keyword = (String) params.get("keyword");
        if (keyword != null) {
            if(keyword.startsWith("author:")){
                Map<String, Object> newParams = copyMap(params);
                newParams.put("keyword", keyword.replace("author:",""));
                return poetryDao.findByAuthorAndLanguage(newParams);
            } else if (keyword.startsWith("tag:")) {
                Map<String, Object> newParams = copyMap(params);
                newParams.put("keyword", keyword.replace("tag:",""));
                return poetryDao.findByTagAndLanguage(newParams);
            }

        }
        return poetryDao.findByKeywordAndLanguage(params);
    }

    @Override
    public List<Poetry> adminListByKeyword(Map<String, Object> params) {
        return poetryDao.adminListByKeyword(params);
    }

    @Override
    public void updateKeywordsById(Map<String, Object> params) {
        poetryDao.updateKeywordsById(params);
    }

    @Override
    public void updateParagrahsSpById(Map<String, Object> params) {
        poetryDao.updateParagrahsSpById(params);
    }

    @Override
    public int updateTagsById(Map<String, Object> params) {
        return poetryDao.updateTagsById(params);
    }

    @Override
    public List<Poetry> find1000ById(int i) {
        return poetryDao.find1000ById(i);
    }

    @Override
    public Poetry findByAuthorAndPoetryBegin(Map<String, String> param) {
        return poetryDao.findByAuthorAndPoetryBegin(param);
    }

    public Map<String,Object> copyMap(Map<String, Object> map){
        Map<String, Object>  resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            resultMap.put(entry.getKey(),entry.getValue());
        }
        return resultMap;
    }
}
