package com.wp.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/6/3 17:05
 * @description 通用的校验结果
 **/
public class ValidationResult {

    private boolean hasErrors = false;


    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }


    private Map<String, String> errorMsgMap = new HashMap<>();

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    /**
     * 实现通用的通过格式化字符串信息获取错误结果的msg方法
     */
    public String getErrMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }
}
