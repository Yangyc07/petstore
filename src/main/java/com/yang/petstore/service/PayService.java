package com.yang.petstore.service;

import com.alipay.api.AlipayApiException;
import com.yang.petstore.alipay.AlipayBean;

//支付服务
public interface PayService {
    String aliPay(AlipayBean alipayBean)throws AlipayApiException;
}
