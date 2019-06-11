package com.wp.miaoshaproject.controller;

import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.response.CommonReturnType;
import com.wp.miaoshaproject.service.OrderService;
import com.wp.miaoshaproject.service.model.OrderModel;
import com.wp.miaoshaproject.service.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.wp.miaoshaproject.error.EmBusinessError.PARAMETER_VALIDATION_ERROR;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/10 19:18
 * @description 处理前端对订单的请求
 **/
@Controller("order")
@RequestMapping("/order")
//跨域请求，保证session发挥作用
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController{

    //public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId", required = false) Integer promoId) throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR, "用户还未登录，无法下单");
        }

        //获取用户信息
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(), itemId, promoId, amount);
        return CommonReturnType.create(null);
    }

}
