package com.wp.miaoshaproject.controller;

import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.response.CommonReturnType;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static com.wp.miaoshaproject.error.EmBusinessError.UNKNOWN_ERROR;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/30 11:12
 * @description 父Controller，主要用来处理异常
 **/
public class BaseController {

    //后端要消费的前端的数据类型
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    /**
    定义解决未被业务controller层吸收的exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {

        Map<String, Object> responseData = new HashMap<>();

        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        }else {
            responseData.put("errCode", UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }
}
