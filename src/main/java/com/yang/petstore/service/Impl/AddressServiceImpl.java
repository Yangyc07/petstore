package com.yang.petstore.service.Impl;

import com.yang.petstore.dao.UserAddressDOMapper;
import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressDOMapper userAddressDOMapper;

    @Override
    public List<UserAddressDO> selectAddressByUserId(Integer userId) {
        return userAddressDOMapper.selectByUserId(userId);
    }

    @Override
    public boolean deleteAddressById(Integer id) {
       return userAddressDOMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean addAddress(UserAddressDO userAddressDO) {
       return userAddressDOMapper.insertSelective(userAddressDO) > 0;
    }

    @Override
    public UserAddressDO selectAddressById(Integer id) {
        return userAddressDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        userAddressDOMapper.deleteByUserId(userId);
    }
}
