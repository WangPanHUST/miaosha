package com.wp.miaoshaproject.service;

import com.wp.miaoshaproject.service.model.PromoModel;

public interface PromoService {

    //根据itemid获取正在进行或即将进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);

}
