package com.wp.miaoshaproject.controller;

import com.wp.miaoshaproject.controller.viewobject.ItemVO;
import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.response.CommonReturnType;
import com.wp.miaoshaproject.service.ItemService;
import com.wp.miaoshaproject.service.model.ItemModel;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/4 11:32
 * @description 处理前端对商品的请求
 **/
@Controller("item")
@RequestMapping("/item")
//跨域请求，保证session发挥作用
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    //public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    @RequestMapping(value = "/create", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "imgUrl") String imgUrl,
                                       @RequestParam(name = "stock") Integer stock) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(price);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setStock(stock);

        //使用createItem之后返回额itemModel，这会向数据库先写入再返回
        ItemModel itemModelForReturn = itemService.createItem(itemModel);
        ItemVO itemVO = this.convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> itemModelList = itemService.listItem();

        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

    //这里使用GET方法，不需要对服务端进行更改，没有consumes，可以在前端直接显示json数据
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id) {
        ItemModel itemModel = itemService.getItemById(id);
        ItemVO itemVO = this.convertVOFromModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    private ItemVO convertVOFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPormoItemPrice());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }else {
            itemVO.setPromoStatus(0);
        }

        return itemVO;
    }
}





















