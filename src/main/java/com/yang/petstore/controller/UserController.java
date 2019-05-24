package com.yang.petstore.controller;



import com.yang.petstore.dataobject.UserAddressDO;
import com.yang.petstore.error.BusinessException;
import com.yang.petstore.error.EmBusinessError;
import com.yang.petstore.response.CommonReturnType;
import com.yang.petstore.service.AddressService;
import com.yang.petstore.service.Model.UserModel;
import com.yang.petstore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
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

    @Autowired
    private AddressService addressService;

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name="telphone")String telphone, @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)|| StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //用户登录服务，用户登录是否合法
        System.out.print(this.EnCodeByMd5(password));
        UserModel userModel = userService.validationLogin(telphone,this.EnCodeByMd5(password));



        //将登陆凭证加入用户登陆成功的session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }


    //用户注册接口
    @RequestMapping(value = "/doRegister",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telphone") String telphone,
                                     @RequestParam(name="optCode")  String optCode,
                                     @RequestParam(name="name")  String name,
                                     @RequestParam(name="gender")  Integer gender,
                                     @RequestParam(name="age")  Integer age,
                                     @RequestParam(name="email")  String email,
                                     @RequestParam(name="password")  String password
                                     ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和optCode是否符合
        String inSessinOptCode = (String)this.httpServletRequest.getSession().getAttribute(telphone);
        System.out.println(inSessinOptCode  + optCode);
        if(!StringUtils.equals(optCode,inSessinOptCode)){
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

    //生成otp短信接口
    @RequestMapping(value = "/createOpt",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOtp(@RequestParam(name="telphone")String telphone){
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
    //用户获取otp短信接口
    @RequestMapping(value = "/getopt",method = {RequestMethod.GET})
    public String getOtp() {
        return "getopt";
    }

    //用户获取otp短信接口
    @RequestMapping(value = "/register",method = {RequestMethod.GET})
    public String register() {
        return "register";
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
    //退出登录
    @RequestMapping(value = "/logOut")
    @ResponseBody
    public String logOut(){
        httpServletRequest.getSession().removeAttribute("LOGIN_USER");
        httpServletRequest.getSession().removeAttribute("IS_LOGIN");
        return "login";
    }

    //修改个人密码
    @RequestMapping(value = "/modifyPassword")
    public String modifyPassword(){
        return "modifypass";
    }

    //个人地址
    @RequestMapping(value = "/myAddress")
    public String myAddres(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        Integer userID =userModel.getId();
        List<UserAddressDO> userAddressDOList = addressService.selectAddressByUserId(userID);
        httpServletRequest.getSession().setAttribute("address",userAddressDOList);
        return "address";
    }

    //编辑个人地址页面
    @RequestMapping(value = "/editAddress")
    public String editAddress(@RequestParam(name="id")Integer id){
        UserAddressDO userAddressDO = addressService.selectAddressById(id);
        httpServletRequest.getSession().setAttribute("myAddress",userAddressDO);
        return "editaddress";
    }

    //编辑个人地址
    @RequestMapping(value = "/doEditAddress")
    @ResponseBody
    public CommonReturnType doEditAddres(
                                       @RequestParam(name="receiver_name") String receiver_name,
                                       @RequestParam(name="receiver_phone")  String receiver_phone,
                                       @RequestParam(name="cmbProvince")  String cmbProvince,
                                       @RequestParam(name="cmbCity")   String cmbcity,
                                       @RequestParam(name="cmbArea")  String cmbArea,
                                       @RequestParam(name="receiver_address")  String receiver_address,
                                       @RequestParam(name="receiver_zip")  String receiver_zip){

        UserAddressDO userAddressDO = (UserAddressDO) httpServletRequest.getSession().getAttribute("myAddress");
        userAddressDO.setReceiverName(receiver_name);
        userAddressDO.setReceiverPhone(receiver_phone);
        userAddressDO.setReceiverProvince(cmbProvince);
        userAddressDO.setReceiverCity(cmbcity);
        userAddressDO.setReceiverDistrict(cmbArea);
        userAddressDO.setReceiverAddress(receiver_address);
        userAddressDO.setReceiverZip(receiver_zip);

        addressService.updateAddress(userAddressDO);
        return CommonReturnType.create(null);
    }


    //删除个人地址
    @RequestMapping(value = "/deleteAddress")
    public String deleteAddress(@RequestParam(name="id")Integer id){
        addressService.deleteAddressById(id);
        return "address";
    }

    //添加地址界面
    @RequestMapping(value = "/addAddress")
    public String addAddress(){
        return "addaddress";
    }

    //添加地址
    @RequestMapping(value = "/doAddAddress")
    @ResponseBody
    public CommonReturnType doAddAddress(@RequestParam(name="receiver_name") String receiver_name,
                               @RequestParam(name="receiver_phone")  String receiver_phone,
                               @RequestParam(name="cmbProvince")  String cmbProvince,
                               @RequestParam(name="cmbCity")   String cmbcity,
                               @RequestParam(name="cmbArea")  String cmbArea,
                               @RequestParam(name="receiver_address")  String receiver_address,
                               @RequestParam(name="receiver_zip")  String receiver_zip){

        UserAddressDO userAddressDO = new UserAddressDO();
        userAddressDO.setReceiverName(receiver_name);
        userAddressDO.setReceiverPhone(receiver_phone);
        userAddressDO.setReceiverProvince(cmbProvince);
        userAddressDO.setReceiverCity(cmbcity);
        userAddressDO.setReceiverDistrict(cmbArea);
        userAddressDO.setReceiverAddress(receiver_address);
        userAddressDO.setReceiverZip(receiver_zip);

        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        userAddressDO.setUserId(userModel.getId());
        addressService.addAddress(userAddressDO);
        return CommonReturnType.create(null);

    }


    //修改个人密码
    @RequestMapping(value = "/doModifyPassword",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType doModifyPassword(@RequestParam(name="password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        if (userService.modifyPassword(userModel.getTelphone(), this.EnCodeByMd5(password))) {
            return CommonReturnType.create(null);
        }
        return null;
    }

}
