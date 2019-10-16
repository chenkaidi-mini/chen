package com.gp4.service;

import com.gp4.pojo.GoodsType;

import java.util.List;

/**
 * ckd 2019/9/11 10:31
 */
public interface GoodsTypeService {
    List<GoodsType> findTypeByLevel(int level);
}
