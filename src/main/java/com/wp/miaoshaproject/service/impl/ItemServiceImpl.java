package com.wp.miaoshaproject.service.impl;

import com.wp.miaoshaproject.dao.ItemDOMapper;
import com.wp.miaoshaproject.dao.ItemStockDOMapper;
import com.wp.miaoshaproject.dataobject.ItemDO;
import com.wp.miaoshaproject.dataobject.ItemStockDO;
import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.service.ItemService;
import com.wp.miaoshaproject.service.PromoService;
import com.wp.miaoshaproject.service.model.ItemModel;
import com.wp.miaoshaproject.service.model.PromoModel;
import com.wp.miaoshaproject.validator.ValidationResult;
import com.wp.miaoshaproject.validator.ValidatorImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.wp.miaoshaproject.error.EmBusinessError.PARAMETER_VALIDATION_ERROR;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/4 10:41
 * @description ItemService的实现类
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    private ValidatorImpl validator;

    @Autowired(required = false)
    private ItemDOMapper itemDOMapper;

    @Autowired(required = false)
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired(required = false)
    private PromoService promoService;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //入参校验
        ValidationResult result = validator.validate(itemModel);
        if (result.isHasErrors()) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //转化itemmodel->dataobject
        ItemDO itemDO = this.convertItemDOFromItemModel(itemModel);

        //写入数据库
        itemDOMapper.insertSelective(itemDO);
        //在插入数据库生成itemDO后才会生成一个自增的id，将数据库中的id赋给itemmodel中的id
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = this.convertItemStockFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //返回创建完成的对象，给前端反馈
        return this.getItemById(itemModel.getId());
    }

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);

        //数据库中price是double类型的，ITEMModel中是BigDecimal，避免类型转化时出现精度丢失
        itemDO.setPrice(itemModel.getPrice().doubleValue());

        return itemDO;
    }

    private ItemStockDO convertItemStockFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = this.convertFromDataObject(itemDO,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    private ItemModel convertFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
        if (itemDO == null) {
            return null;
        }
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);

        //从数据库data向itemmodel转的时候也要注意price的类型问题
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());

        return itemModel;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {

            return null;
        }

        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(id);
        ItemModel itemModel = this.convertFromDataObject(itemDO, itemStockDO);

        //获取活动商品信息
        PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
        if (promoModel != null && promoModel.getStatus().intValue() != 3) {
            itemModel.setPromoModel(promoModel);
        }

        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStcok(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        if (affectedRow > 0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
        itemDOMapper.increaseSales(itemId, amount);
    }

}
