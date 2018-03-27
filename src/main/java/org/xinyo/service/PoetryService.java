package org.xinyo.service;

import org.xinyo.domain.Poetry;

/**
 * Created by chengxinyong on 2018/3/27.
 */
public interface PoetryService {
    Poetry findPoetryById(Integer poetryId);
}
