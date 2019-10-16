package com.gp4.service.impl;

import com.gp4.dao.GoodsTypeMapper;
import com.gp4.pojo.GoodsType;
import com.gp4.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ckd 2019/9/11 10:31
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findTypeByLevel(int level) {
        return goodsTypeMapper.findByLevel(level);
    }
}
