package com.wp.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/5 16:51
 * @description 订单的领域模型
 **/
public class OrderModel {

    //订单号采用String类型，保存的各个字段都有意义
    private String id;

    private Integer userId;

    private Integer itemId;

    //若promoId非空，表示秒杀价格
    private BigDecimal itemPrice;

    private Integer amount;

    //总金额
    private BigDecimal orderPrice;

    //若非空表示以秒杀活动价格下单
    private Integer promoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

}
