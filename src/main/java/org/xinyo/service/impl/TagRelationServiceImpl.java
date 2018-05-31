package org.xinyo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xinyo.dao.TagRelationDao;
import org.xinyo.domain.TagRelation;
import org.xinyo.service.TagRelationService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/4/28.
 */
@Service
public class TagRelationServiceImpl implements TagRelationService {
    @Autowired
    private TagRelationDao tagRelationDao;

    @Override
    public TagRelation selectByTag(TagRelation tagRelation) {
        return tagRelationDao.selectByTag(tagRelation);
    }

    @Override
    public int add(TagRelation tagRelation) {
        return tagRelationDao.insert(tagRelation);
    }

    @Override
    public int updateConnection(TagRelation tagRelation) {
        return tagRelationDao.updateConnection(tagRelation);
    }

    @Override
    public int batchInsert(List<TagRelation> list) {
        return tagRelationDao.batchInsert(list);
    }

    @Override
    public Map<String, Integer> selectByKeyword(String keyword) {
        List<TagRelation> tagRelationList = tagRelationDao.selectByKeyword(keyword);

        Map<String, Integer> relationMap = new LinkedHashMap<>();

        int max = -1;
        // 进行重新排序
        for (TagRelation tagRelation : tagRelationList) {
            if (max == -1) {
                max = tagRelation.getConnection();
            }

            int rank = ~~ (tagRelation.getConnection() * 100 / max);
            if (keyword.equals(tagRelation.getTagA())) {
                relationMap.put(tagRelation.getTagB(), rank);
            } else {
                relationMap.put(tagRelation.getTagA(), rank);
            }
        }

        return relationMap;
    }
}
