package com.gp4.dao;

import com.gp4.pojo.GoodsType;

import java.util.List;

/**
 * ckd 2019/9/27 8:35
 */
public interface GoodsTypeMapper {

    List<GoodsType> findByLevel(int level);

    GoodsType findById(int typeid);
}
