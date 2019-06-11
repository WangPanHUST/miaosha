package com.wp.miaoshaproject.service.impl;

import com.wp.miaoshaproject.dao.UserDOMapper;
import com.wp.miaoshaproject.dao.UserPasswordDOMapper;
import com.wp.miaoshaproject.dataobject.UserDO;
import com.wp.miaoshaproject.dataobject.UserPasswordDO;
import com.wp.miaoshaproject.error.BusinessException;
import com.wp.miaoshaproject.service.UserService;
import com.wp.miaoshaproject.service.model.UserModel;
import com.wp.miaoshaproject.validator.ValidationResult;
import com.wp.miaoshaproject.validator.ValidatorImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.wp.miaoshaproject.error.EmBusinessError.PARAMETER_VALIDATION_ERROR;
import static com.wp.miaoshaproject.error.EmBusinessError.USER_LOGIN_FAIL;

/**
 * @author WangPan wangpanhust@qq.com
 * @date 2019/5/29 18:54
 * @description UserService的实现类
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDOMapper userDOMapper;

    @Autowired(required = false)
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired(required = false)
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        /*
        UserDO不能直接透传给前端，这数据库数据的全部映射，但是前端需要的数据只是一部分，且与业务模型不一致
         */
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }

        //selectByUserId是在Mapper中自己添加的方法
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO, userPasswordDO);
    }

    @Transactional
    @Override
    public void register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR);
        }

//        if (StringUtils.isEmpty(userModel.getName())
//                || userModel.getAge() == null
//                || userModel.getGender() == null
//                || StringUtils.isEmpty(userModel.getTelphone())) {
//            throw new BusinessException(PARAMETER_VALIDATION_ERROR);
//        }
        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }

        //使用insertSelective方法时，如果待插入字段值为null，则不插入该字段，使用数据库的默认值
        UserDO userDO = convertFromUserModel(userModel);
        userDOMapper.insertSelective(userDO);

        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException{
        //通过用户手机号获取用户信息
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO == null) {
            throw new BusinessException(USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);

        //将数据库中的密码与输入的密码进行比对
        if (!StringUtils.equals(encrptPassword, userModel.getEncrptPassword())) {
            throw new BusinessException(USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserDO convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }

    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncryptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);

        if (userPasswordDO.getEncryptPassword() != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncryptPassword());
        }

        return userModel;
    }
}
