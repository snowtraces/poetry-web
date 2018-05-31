package org.xinyo.service;

import org.xinyo.domain.TagRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by chengxinyong on 2018/4/28.
 */
public interface TagRelationService {
    TagRelation selectByTag(TagRelation tagRelation);

    int add(TagRelation tagRelation);

    int updateConnection(TagRelation tagRelation);

    int batchInsert(List<TagRelation> list);

    Map<String, Integer> selectByKeyword(String keyword);
}
