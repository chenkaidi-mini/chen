package com.gp4.dao;

import com.gp4.pojo.Goods;

import java.util.List;

/**
 * ckd 2019/9/27 8:35
 */
public interface GoodsMapper {

    long getCount(int typeid);

    List<Goods> findPageByWhere(int pageNum, int pageSize, int typeid);

    Goods findById(int gid);
}
