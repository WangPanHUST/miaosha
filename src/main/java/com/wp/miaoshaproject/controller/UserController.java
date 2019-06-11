package com.wp.miaoshaproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.wp.miaoshaproject.controller.viewobject.UserVO;
import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.response.CommonReturnType;
import com.wp.miaoshaproject.service.UserService;
import com.wp.miaoshaproject.service.model.UserModel;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

import static com.wp.miaoshaproject.error.EmBusinessError.PARAMETER_VALIDATION_ERROR;
import static com.wp.miaoshaproject.error.EmBusinessError.USER_NOT_EXIST;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/24 21:05
 * @description 处理前端对用户的请求
 **/
@Controller("user")
@RequestMapping("/user")
//跨域请求，保证session发挥作用
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                    @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR);
        }

        //调用登录服务
        UserModel userModel = userService.validateLogin(telphone, this.encodeByMD5(password));

        //将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    @Transactional
    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                       @RequestParam(name = "otpCode") String otpCode,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "age") Integer age,
                                       @RequestParam(name = "gender") Integer gender,
                                       @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的otpcode一致
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR, "验证码输入有误");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncrptPassword(this.encodeByMD5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 加密字符串
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone") String telphone) {
        //生成随机OTPCODE，一次性密码（验证码）
        Random random = new Random();
        int randomCode = random.nextInt(99999);
        randomCode += 10000;
        String otpCode = String.valueOf(randomCode);

        //将OTP验证与对应用户的手机号进行关联，这里使用Http session，企业分布式应用中使用redis
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        //将OTPCODE通过短信发送给用户，省略
        System.out.println("telphone = " + telphone + " & otpcode = " + otpCode);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException {
        /*
        通过调用service服务获取对应id的用户模型
         */
        UserModel userModel = userService.getUserById(id);
        UserVO userVO = convertFromUserModel(userModel);
        if (userVO == null) {
            //如果用户不存在，抛出自定义的exception
            throw new BusinessException(USER_NOT_EXIST);
        }
        return CommonReturnType.create(userVO);
    }

    /**
    将核心领域模型转换为供前端视图展示的viewobject
     */
    private UserVO convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
