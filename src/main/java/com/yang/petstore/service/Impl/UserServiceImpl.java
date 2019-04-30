package com.yang.petstore.service.Impl;

import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dao.UserPasswordDoMapper;
import com.yang.petstore.dataobject.UserDO;
import com.yang.petstore.dataobject.UserPasswordDo;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.UserService;
import com.yang.petstore.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDoMapper userPasswordDoMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        return null;
    }

    @Override
    public void register(UserModel userModel) {

    }


    @Override
    public UserModel validationLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过手机号获取用户信息
        System.out.println(telphone);
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if(userDO == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //获取用户的密码信息
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByPrimaryKey(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO,userPasswordDo);
        return userModel;
    }

    //将object转化为Model对象
    private UserModel convertFromDataObject(UserDO userDO,UserPasswordDo userPasswordDo) {
        if(userDO == null || userPasswordDo == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        BeanUtils.copyProperties(userPasswordDo,userModel);
        return userModel;
    }
}
