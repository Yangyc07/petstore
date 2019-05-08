package com.yang.petstore.controller;



import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = {"*"}) // 支持跨域
public class UserController extends BaseController{


    private static final Logger lg = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telphone")String telphone,@RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)|| StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //用户登录服务，用户登录是否合法
        System.out.print(this.EnCodeByMd5(password));
        UserModel userModel = userService.validationLogin(telphone,this.EnCodeByMd5(password));

        //将登陆凭证加入用户登陆成功的session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

        lg.info("自动生成session [{}]", httpServletRequest.getSession().getAttribute("LOGIN_USER").toString());
        return CommonReturnType.create(null);
    }


    //用户注册接口
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telphone") String telphone,
                                     @RequestParam(name="optCode")  String optCode,
                                     @RequestParam(name="name")  String name,
                                     @RequestParam(name="gender")  Integer gender,
                                     @RequestParam(name="age")  Integer age,
                                     @RequestParam(name="email")  String email,
                                     @RequestParam(name="password")  String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和optCode是否符合
        String inSessinOptCode = (String)this.httpServletRequest.getSession().getAttribute(telphone);
        if(com.alibaba.druid.util.StringUtils.equals(optCode,inSessinOptCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不匹配");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setEmail(email);
        userModel.setRegisterDate(DateTime.now().toDate());
        userModel.setEncrptPassword(this.EnCodeByMd5(password));
        userService.register(userModel);
        return  CommonReturnType.create(null);
    }

    //用户获取otp短信接口
    @RequestMapping(value = "/getopt",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone")String telphone){
        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String optCode = String.valueOf(randomInt);
        //将OPT验证码同对应用户的手机号关联(可以使用redis)
        //目前使用httpsession
        httpServletRequest.getSession().setAttribute(telphone,optCode);
        //将OPT验证码通过短信通道发送给用户（暂时不做）
        System.out.println("telphone="+telphone +"optCode="+optCode);
        return  CommonReturnType.create(null);
    }

    //删除用户
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType deleteUser(@RequestParam(name="id")Integer id){
        userService.deleteUser(id);
        return CommonReturnType.create(null);
    }

    //MD5加密
    public String EnCodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}
