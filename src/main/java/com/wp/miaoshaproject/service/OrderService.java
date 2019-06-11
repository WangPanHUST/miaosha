package com.wp.miaoshaproject.service;

import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.service.model.OrderModel;

public interface OrderService {

    //1、前端url上传递秒杀活动id，下单接口内校验对应id是否属于对应商品且活动已开始（推荐）
    //2、直接在下单接口内判断对应商品是否存在秒杀活动，若存在则以秒杀价格下单（会检验两次是否是秒杀商品）
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;
}
