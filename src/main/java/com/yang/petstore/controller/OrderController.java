package com.yang.petstore.controller;


import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class OrderController extends BaseController{


    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //生成订单
    @RequestMapping(value = "/createOrder",method = {RequestMethod.GET})
    String createOrder(Integer addressId){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderVO orderVO = orderService.createOrder(userModel.getId(),addressId);
        httpServletRequest.getSession().setAttribute("orderVO",orderVO);
        return "myorder";
    }


    //完成支付
    @RequestMapping(value = "/completePay",method = {RequestMethod.POST})
    @ResponseBody
    CommonReturnType completePay(String orderNo) throws BusinessException {
        if (orderService.completePay(orderNo)) {
            return CommonReturnType.create(null);
        }
        return CommonReturnType.create(new BusinessException(EmBusinessError.UNKNOWN_ERROR));
    }
}
