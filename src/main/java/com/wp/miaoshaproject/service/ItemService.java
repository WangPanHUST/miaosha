package com.wp.miaoshaproject.service;

import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.service.model.ItemModel;

import java.util.List;

public interface ItemService {
    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //库存扣减
    boolean decreaseStcok(Integer itemId, Integer amount) throws BusinessException;

    //销量增加
    void increaseSales(Integer itemId, Integer amount) throws BusinessException;
}
