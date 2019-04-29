package com.yang.petstore.controller;


import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dataobject.UserDO;
import com.yang.petstore.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.yang.petstore.controller.BaseController.CONTENT_TYPE_FORMED;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//跨域请求
public class UserController {

    @Autowired
    private UserDOMapper userDOMapper;

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType Login(@RequestParam(name="id")Integer id){
       UserDO userDO = userDOMapper.selectByPrimaryKey(id);
       return CommonReturnType.create(userDO);
    }
}
