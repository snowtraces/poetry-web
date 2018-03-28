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


}
