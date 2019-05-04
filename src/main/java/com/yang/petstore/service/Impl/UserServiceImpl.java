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
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "user")
    /**
     * create by: yyc
     * 将方法的运行结果进行缓存，以后要取出相同的数据,直接从缓存中取
     * create time: 2019/5/4 20:33
     * CacheManger管理多个Cache组件
     *      几个属性
     *      cacheName/value:指定缓存组件的名字
     *      key：缓存数据使用的Key，默认是方法参数的值，编写SpeE: #id：参数id的值
     *      keyCenerator：key的生成器，可以自己定义
     *      cacheManager：决定从哪个缓存组件拿数据：或者缓存解析器resolver
     *      condition：制定符合条件的情况下才缓存 : #id > 0
     *      unless:否定缓存
     *      sync：时候使用异步
     *
     * 1.自动配置类
     * 2.缓存的配置类
     * 3.simpleCacheConfiguration生效
     * 4.给容器中注册了一个缓存管理器
     * 5.可以获取CurrentMapCache类型的缓存组件，将数据保存
     *
     * 如何运行
     * @Cacheable
     * 1.方法运行之前，查询Cache，按照cacheName获取cache
     * 2.
     * @return com.yang.petstore.service.Model.UserModel
     */
    public UserModel validationLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过手机号获取用户信息
        System.out.println(telphone);
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if(userDO == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //获取用户的密码信息
        UserPasswordDo userPasswordDo = userPasswordDoMapper.selectByPrimaryKey(userDO.getId());
        if(userPasswordDo.getEncrptPassword().equals(encrptPassword)) {
            UserModel userModel = convertFromDataObject(userDO, userPasswordDo);
            return userModel;
        }
        else
            return null;
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
