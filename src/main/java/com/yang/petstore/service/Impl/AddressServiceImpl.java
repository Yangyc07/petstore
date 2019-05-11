package com.yang.petstore.service.Impl;

import com.yang.petstore.dao.UserAddressDOMapper;
import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.service.Model.AddressService;
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
}
