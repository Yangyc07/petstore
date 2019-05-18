package com.yang.petstore.service.Impl;

import com.alipay.api.AlipayApiException;
import com.yang.petstore.alipay.AlipayBean;
import com.yang.petstore.alipay.AlipayUtil;
import com.yang.petstore.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}
