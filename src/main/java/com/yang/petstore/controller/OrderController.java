package com.yang.petstore.controller;


import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    String createOrder(Integer userId){
        Integer addressId = 1;
        OrderVO orderVO = orderService.createOrder(userId,addressId);
        httpServletRequest.getSession().setAttribute("orderVO",orderVO);
        return "myorder";
    }
}
