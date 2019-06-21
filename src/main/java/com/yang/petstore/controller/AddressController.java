package com.yang.petstore.controller;


import com.yang.petstore.dao.UserAddressDOMapper;
import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.AddressService;
import com.yang.petstore.service.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller("address")
@RequestMapping("/address")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class AddressController extends BaseController{


    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    UserAddressDOMapper userAddressDOMapper;

    @Autowired
    AddressService addressService;

    //增加用户地址
    @RequestMapping(value = "/addAddress",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    CommonReturnType addAddress(@RequestParam(name = "receiverName")String receiverName,
                                @RequestParam(name = "receiverPhone")String receiverPhone,
                                @RequestParam(name = "receiverProvince")String receiverProvince ,
                                @RequestParam(name = "receiverCity")String receiverCity,
                                @RequestParam(name = "receiverDistrict")String receiverDistrict,
                                @RequestParam(name = "receiverAddress")String receiverAddress,
                                @RequestParam(name = "zip")String zip)throws BusinessException{
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        UserAddressDO userAddressDO = new UserAddressDO();
        userAddressDO.setUserId(userModel.getId());
        userAddressDO.setReceiverName(receiverName);
        userAddressDO.setReceiverPhone(receiverPhone);
        userAddressDO.setReceiverProvince(receiverProvince);
        userAddressDO.setReceiverCity(receiverCity);
        userAddressDO.setReceiverDistrict(receiverDistrict);
        userAddressDO.setReceiverAddress(receiverAddress);

        addressService.addAddress(userAddressDO);
        return CommonReturnType.create(userAddressDO);
    }

    //查询用户地址
    @RequestMapping(value = "/myAddress",method = {RequestMethod.GET})
    @ResponseBody
    CommonReturnType myAddress(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        return CommonReturnType.create(addressService.selectAddressByUserId(userModel.getId()));
    }

    //查询用户地址
    @RequestMapping(value = "/selectAddress",method = {RequestMethod.GET})
    String selectAddress(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        List<UserAddressDO> userAddressDOS = addressService.selectAddressByUserId(userModel.getId());
        httpServletRequest.getSession().setAttribute("userAddressDOS",userAddressDOS);
        return "address";
    }

    //删除用户地址
    @RequestMapping(value = "/deleteAddressById",method = {RequestMethod.GET})
    String deleteAddress(Integer id){
        addressService.deleteAddressById(id);
        return "address";
    }

}
