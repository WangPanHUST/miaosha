package com.wp.miaoshaproject.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/10 21:34
 * @description 秒杀活动的领域模型
 **/
public class PromoModel {

    private Integer id;

    //秒杀活动状态，1表示还未开始，2表示正在进行，3表示已经结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //秒杀活动开始时间
    private DateTime startDate;

    //秒杀活动结束时间
    private DateTime endDate;

    //参加秒杀活动的商品名称
    private Integer itemId;

    //秒杀价格
    private BigDecimal pormoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPormoItemPrice() {
        return pormoItemPrice;
    }

    public void setPormoItemPrice(BigDecimal pormoItemPrice) {
        this.pormoItemPrice = pormoItemPrice;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
