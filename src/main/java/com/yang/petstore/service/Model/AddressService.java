package com.yang.petstore.service.Model;

import com.yang.petstore.dataobject.UserAddressDO;

import java.util.List;

public interface AddressService {

    //查询地址
    List<UserAddressDO> selectAddressByUserId(Integer userId);
}
