package com.yang.petstore.service.Impl;

import com.sun.tools.internal.ws.wsdl.framework.DuplicateEntityException;
import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dao.UserPasswordDOMapper;
import com.yang.petstore.dataobject.UserDO;
import com.yang.petstore.dataobject.UserPasswordDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.UserService;
import com.yang.petstore.validator.ValidationResult;
import com.yang.petstore.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user",cacheManager = "cacheManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        //调用usermapper获取对应的dataobject
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) {
            return null;
        }
        //通过用户id获取对应的加密密码信息
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        return  convertFromDataObject(userDO,userPasswordDO);
    }

    @Override
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }

        //实现model -> dataobject
        UserDO userDO = convertFromModel(userModel);
        try{
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateEntityException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"该手机号已注册");
        }

        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
        return;
    }

    //将Model转化为dataobject
    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if(userModel == null){
            return null;
        }
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return  userPasswordDO;
    }


    //将Model转化为dataobject
    private UserDO convertFromModel(UserModel userModel) {
        if(userModel == null){
            return null;
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return  userDO;
    }


    /**
     * create by: yyc
     * 将方法的运行结果进行缓存，以后要取出相同的数据,直接从缓存中取
     * create time: 2019/5/4 20:33
     * CacheManger管理多个Cache组件
     *      几个属性
     *      cacheName/value:指定缓存组件的名字，数组的方式
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
     * 1.方法运行之前，查询Cache，按照cacheName获取cache，如果没有，则自动创建
     * 2.去cache中查找缓存内容，使用一个key，默认是方法的参数
     * key是按照某种策略生成的：默认是使用keyGeneretor生成，使用SimpleKeyCeneretor生成key
     *      SimpleKeyCeneretor生成key
     *          如果没有参数，key=new SimpleKeyCeneretor（）;
     *          如果一个参数，key=参数的值
     *          如果多个参数，key=new SimpleKeyCeneretor（params）;
     * 3.没有查到缓存就调用目标方法：
     * 4.将目标方法返回的数据放入缓存
     *
     * @return com.yang.petstore.service.Model.UserModel
     */

    /**
     * @CachePut:既调用方法，又更新缓存
     * 修改了数据库的某个数据，同时更新缓存
     *  运行时机
     *      1.先调用目标方法
     *      2.将目标方法的结果缓存起来
     *
     * create time: 2019/5/5 9:39
     *
     * @re * @Param: nullParam: null
     */
    @Override
    @Cacheable(keyGenerator = "keyGenerator")
    public UserModel validationLogin(String telphone, String encrptPassword) throws BusinessException {
        //通过用户的手机获取用户信息
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if(userDO == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO,userPasswordDO);
        //然后比较
        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }


    //清楚缓存
    /** allEntries = true 清除所有缓存
     *  beforeInvocation = true 在方法执行之前清楚
     *
     * @Param: id
     * @return boolean
     */
    @Override
    public boolean deleteUser(Integer id) {
       return userDOMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean modifyPassword(String telphone, String password) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if(userDO == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        userPasswordDO.setEncrptPassword(password);
        return userPasswordDOMapper.updateByPrimaryKey(userPasswordDO) > 0;
    }

    //将object转化为Model对象
    private UserModel convertFromDataObject(UserDO userDO,UserPasswordDO userPasswordDO) {
        if(userDO == null || userPasswordDO == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        BeanUtils.copyProperties(userPasswordDO,userModel);
        return userModel;
    }
}
