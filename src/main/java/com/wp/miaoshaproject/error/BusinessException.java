package com.wp.miaoshaproject.error;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/29 21:50
 * @description 包装器业务异常类实现
 **/
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    /**
    直接接收EmBusinessError的传参用于构造业务异常
     */
    public BusinessException(CommonError commonError) {
        //父类的Exception有自己的初始化过程
        super();
        this.commonError = commonError;
    }

    /**
     接收自定义errMsg的方式构造业务异常
     */
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
