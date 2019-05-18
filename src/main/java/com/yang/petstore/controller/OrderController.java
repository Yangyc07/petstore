package com.yang.petstore.controller;



import com.yang.petstore.controller.ViewObject.OrderInfoVO;
import com.yang.petstore.controller.ViewObject.OrderVO;
import com.yang.petstore.dataobject.OrderDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.OrderService;
import com.yang.petstore.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class OrderController extends BaseController{


    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private PayService payService;

    //生成订单
    @RequestMapping(value = "/createOrder",method = {RequestMethod.GET})
    String createOrder(Integer addressId){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderVO orderVO = orderService.createOrder(userModel.getId(),addressId);
        httpServletRequest.getSession().setAttribute("orderVO",orderVO);
        return "myorder";
    }




    //查看我的所有订单
    @RequestMapping(value = "/myOrder",method = {RequestMethod.GET})
    String myOrder(Integer userId){
        List<OrderVO> orderVOS = orderService.selectByUserId(userId);
        httpServletRequest.getSession().setAttribute("orderVOS",orderVOS);
        return "allorder";
    }


    //查看详细订单
    @RequestMapping(value = "/getOrder",method = {RequestMethod.GET})
    String getOrder(String orderNo){
        List<OrderVO> orderList = (List<OrderVO>)httpServletRequest.getSession().getAttribute("orderVOS");
        for (OrderVO orderVO:orderList) {
            if(orderVO.getOrderNo().equals(orderNo)){
                httpServletRequest.getSession().setAttribute("orderVO",orderVO);
                break;
            }
        }
        return "myorder";
    }

}
