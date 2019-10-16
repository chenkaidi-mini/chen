package com.gp4.pojo;

import java.math.BigDecimal;

/**
 * ckd 2019/9/10 14:34
 */
public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer tid;
    private Integer num;
    private BigDecimal money;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, String oid, Integer tid, Integer num, BigDecimal money) {
        this.id = id;
        this.oid = oid;
        this.tid = tid;
        this.num = num;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", oid='" + oid + '\'' +
                ", tid=" + tid +
                ", num=" + num +
                ", money=" + money +
                '}';
    }
}
