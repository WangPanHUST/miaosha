package com.wp.miaoshaproject.service.impl;

import com.wp.miaoshaproject.dao.PromoDOMapper;
import com.wp.miaoshaproject.dataobject.PromoDO;
import com.wp.miaoshaproject.service.PromoService;
import com.wp.miaoshaproject.service.model.PromoModel;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/11 14:45
 * @description PromoService的实现类
 **/
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired(required = false)
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel = this.convertFromDataObject(promoDO);
        if (promoModel == null) {
            return null;
        }

        //判断秒杀活动状态
        if (promoModel.getStartDate().isAfterNow()) {
            promoModel.setStatus(1);
        }else if (promoModel.getEndDate().isBeforeNow()) {
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertFromDataObject(PromoDO promoDO) {
        if (promoDO == null) {
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setPormoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        return promoModel;
    }
}
