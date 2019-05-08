package com.yang.petstore.controller;


import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class OrderController extends BaseController{


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/createOrder",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    CommonReturnType createOrder(Integer userId ,Integer addressId){
        OrderVO orderVO = orderService.createOrder(userId,addressId);
        return CommonReturnType.create(orderVO);
    }

}
