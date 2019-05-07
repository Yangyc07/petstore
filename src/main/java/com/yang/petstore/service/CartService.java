package com.yang.petstore.service;

import com.yang.petstore.error.BusinessException;
import com.yang.petstore.service.Model.ItemModel;

import java.util.List;

public interface CartService {
    //添加商品至我的购物车
    boolean addItemToCart(Integer userId,Integer itemId,Integer amount) throws BusinessException;

    //我的购物车
    List<ItemModel> myCart(Integer userId);
}
