package com.yang.petstore.controller;


import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.CartService;
import com.yang.petstore.service.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("cart")
@RequestMapping("/cart")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //添加商品至购物车
    @RequestMapping(value = "/addItemToCart",method = {RequestMethod.POST})//Content type 'null' not supported
    @ResponseBody
    CommonReturnType addItemToCart(Integer itemId,Integer amount) throws BusinessException {
        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        if(!cartService.addItemToCart(userModel.getId(), itemId, amount)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"添加至购物车失败");
        }
        return CommonReturnType.create(null);
    }

    //获取我的购物车信息
    @RequestMapping(value = "/myCart",method = {RequestMethod.POST})//Content type 'null' not supported
    @ResponseBody
    CommonReturnType myCart(Integer userId)throws BusinessException{
        //1.根据userId获取我的购物车信息
        return null;
    }


}
