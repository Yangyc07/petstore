package com.yang.petstore.service;

import com.yang.petstore.controller.ViewObject.CartVO;
import com.yang.petstore.error.BusinessException;

public interface CartService {
    //添加商品至我的购物车
    boolean addItemToCart(Integer userId,Integer itemId,Integer amount) throws BusinessException;

    //我的购物车
    CartVO myCart(Integer userId);

    //将商品移出购物车
    boolean deleteItem(Integer itemId);
}
