package com.yang.petstore.service;

import com.yang.petstore.controller.ViewObject.OrderVO;

public interface OrderService {
    //生成订单
    OrderVO createOrder(Integer userId, Integer shippingId);
}
