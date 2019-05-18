package com.yang.petstore.controller;

import com.alipay.api.AlipayApiException;
import com.yang.petstore.alipay.AlipayBean;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.service.OrderService;
import com.yang.petstore.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController("pay")
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    //阿里支付界面
    @RequestMapping(value = "aliPay",method = {RequestMethod.POST})
    String aliPay(String out_trade_no,String total_amount) throws BusinessException, AlipayApiException {
        String subject = "测试";
        String body = "测试";

        orderService.completePay(out_trade_no);

        return payService.aliPay(new AlipayBean()
                .setBody(body)
                .setOut_trade_no(out_trade_no)
                .setTotal_amount(new StringBuffer().append(total_amount))
                .setSubject(subject));
    }
}
