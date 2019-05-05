package com.yang.petstore.service;

import com.yang.petstore.error.BusinessException;
import com.yang.petstore.service.Model.UserModel;

public interface UserService {
    //通过id获取用户对象
    UserModel getUserById(Integer id);

    //用户注册
    void register(UserModel userModel) throws BusinessException;

    //用户登陆验证
    UserModel validationLogin(String telphone,String encrptPassword) throws BusinessException;

    //删除用户
    boolean deleteUser(Integer id);

}
