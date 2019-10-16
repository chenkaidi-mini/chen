package com.gp4.service.impl;

import com.gp4.dao.GoodsMapper;
import com.gp4.dao.GoodsTypeMapper;
import com.gp4.pojo.Goods;
import com.gp4.pojo.GoodsType;
import com.gp4.pojo.PageBean;
import com.gp4.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ckd 2019/9/11 11:20
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public PageBean<Goods> findPageByWhere(int pageNum, int pageSize, int typeid) {
        long totalSize = goodsMapper.getCount(typeid);
        List<Goods> data = goodsMapper.findPageByWhere(pageNum,pageSize,typeid);
        PageBean<Goods> pageBean = new PageBean<>(pageNum,pageSize,totalSize,data);
        return pageBean;
    }

    @Override
    public Goods findGoodsById(int id) {
        Goods goods = goodsMapper.findById(id);
        GoodsType goodsType = goodsTypeMapper.findById(goods.getTypeid());
        goods.setGoodsType(goodsType);
        return goods;
    }
}
