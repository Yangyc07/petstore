package com.yang.petstore.controller;

import com.alipay.api.AlipayApiException;
import com.yang.petstore.alipay.AlipayBean;
import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.OrderService;
import com.yang.petstore.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController("pay")
@RequestMapping("/pay")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
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

    //同步回调
    @RequestMapping(value = "/getReturnUrlInfo",method = RequestMethod.GET)
    String alipayNotifyUrlInfo(HttpServletRequest request){
        payService.synchronous(request);
        //获取订单号
        String out_trade_no = request.getParameter("out_trade_no");
        //根据订单号获取订单信息
        OrderVO orderVO = orderService.selectByOrderNo(out_trade_no);
        request.getSession().setAttribute("orderVO",orderService.selectByOrderNo(out_trade_no));

        //界面必须部署在公网上
        return "myorder";
    }
}
