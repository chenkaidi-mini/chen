package com.gp4.controller;

import com.alibaba.fastjson.JSON;
import com.gp4.pojo.GoodsType;
import com.gp4.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ckd 2019/10/7 10:43
 */

@Controller
@RequestMapping("goodstype")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;
    @RequestMapping("list")
    public String goodstypelist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        List<GoodsType> goodsTypeList =  goodsTypeService.findTypeByLevel(1);
        String json = JSON.toJSONString(goodsTypeList);
        response.getWriter().write(json);
        return null;
    }
}
