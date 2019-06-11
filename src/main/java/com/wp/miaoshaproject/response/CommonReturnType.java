package com.wp.miaoshaproject.response;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/29 20:58
 * @description 定义一个通用的返回类型，前端可以读取status判断是服务器错误还是其他错误
 **/
public class CommonReturnType {
    //表明对应请求的返回处理结果“success”或“fail"
    private String status;

    //若status=success，则data内返回前端需要的json数据
    //若status=fail，则data内使用通用的错误码格式（http状态码）
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //定义一个通用的创建方法，这里使用static方式，可以直接使用使用类名访问
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");

    }
    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }
}
