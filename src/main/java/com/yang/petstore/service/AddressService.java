package com.yang.petstore.service;

import com.yang.petstore.dataobject.UserAddressDO;

import java.util.List;

public interface AddressService {

    //查询地址
    List<UserAddressDO> selectAddressByUserId(Integer userId);

    //删除地址
    boolean deleteAddressById(Integer id);

    //增加地址信息
    boolean addAddress(UserAddressDO userAddressDO);

    //根据id查找地址
    UserAddressDO selectAddressById(Integer id);

    void deleteByUserId(Integer userId);

    //更新地址信息
    boolean updateAddres(UserAddressDO userAddressDO);

}
