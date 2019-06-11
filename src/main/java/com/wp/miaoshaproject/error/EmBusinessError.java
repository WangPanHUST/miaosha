package com.wp.miaoshaproject.error;

public enum EmBusinessError implements CommonError{

    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //2开头为用户相关错误
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20002, "手机号或密码错误"),
    USER_NOT_LONGIN(20003, "用户还未登录"),

    //3000开头为交易信息错误
    STOCK_NOT_ENOUGH(30001, "库存不够")
    ;

    private int errCode;
    private String errMsg;

    /**
    这是一个private的构造器，供上面定义的枚举具体类使用
     */
    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }


    /**
     * 定值化的方式去改动错误类型，比如对通用参数类型错误，可以具体指定哪个参数出错
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
