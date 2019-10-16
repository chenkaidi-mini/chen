package com.gp4.service;

import com.gp4.pojo.Goods;
import com.gp4.pojo.PageBean;

/**
 * ckd 2019/9/11 11:20
 */
public interface GoodsService {
    PageBean<Goods> findPageByWhere(int pageNum, int pageSize, int typeid);

    Goods findGoodsById(int id);
}
