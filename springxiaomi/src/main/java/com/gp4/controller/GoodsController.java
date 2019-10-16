package com.gp4.controller;

import com.gp4.pojo.Goods;
import com.gp4.pojo.PageBean;
import com.gp4.service.GoodsService;
import com.gp4.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * ckd 2019/10/7 10:48
 */

@Controller
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("listbytypeid")
    public String getGoodsListByTypeId(HttpServletRequest request){
        String typeId = request.getParameter("typeId");
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int pn=1;
        int ps=2;
        if (!StringUtils.isEmpty(pageNum)){
            pn = Integer.parseInt(pageNum);
            if (pn<1){
                pn=1;
            }
        }
        if (!StringUtils.isEmpty(pageSize)){
            ps = Integer.parseInt(pageSize);
            if (ps<1){
                ps=8;
            }
        }
        int tid = 0;
        if (typeId!=null&&typeId.trim().length()!=0){
            tid = Integer.parseInt(typeId);
        }
        try {
            pn = (pn-1)*ps;
            PageBean<Goods> pageBean = goodsService.findPageByWhere(pn, ps, tid);
            request.setAttribute("pageBean", pageBean);
            request.setAttribute("typeId", typeId);
            return "forward:/goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/index.jsp";
        }
    }

    @RequestMapping("byid")
    public String getGoodsById(HttpServletRequest request){
        String id = request.getParameter("id");
        if(StringUtils.isEmpty(id)){
            return "redirect:/index.jsp";
        }
        Goods goods = goodsService.findGoodsById(Integer.parseInt(id));
        request.setAttribute("goods", goods);
        return "forward:/goodsDetail.jsp";
    }
}
