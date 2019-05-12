package com.yang.petstore.service;

import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.error.BusinessException;

public interface OrderService {
    //生成订单
    OrderVO createOrder(Integer userId, Integer shippingId);

    //完成支付
    boolean completePay(String orderNo) throws BusinessException;
}
