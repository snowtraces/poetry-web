package org.xinyo.service;

import org.xinyo.domain.Author;

import java.util.Map;

/**
 * Created by CHENG on 2018/3/31.
 */
public interface AuthorService {
    Author findByIdAndLanguage(Map<String, Object> params);
}
