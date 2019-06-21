package com.yang.petstore;



import com.yang.petstore.dao.UserDOMapper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@SpringBootApplication(scanBasePackages = {"com.yang.petstore"})
@MapperScan("com.yang.petstore.dao")
@Controller
//@EnableCaching //开启缓存


/**
 *  Springboot整合redis
 *  1.安装redis
 *  2.引入redis的starter
 *  3.配置redis
 *
 */
public class PetstoreApplication {

	@Autowired
	public UserDOMapper userDOMapper;

	@RequestMapping("/hello")
	public String index(Model model){
		model.addAttribute("name", "Dear");
		return "login";
	}

	@RequestMapping("/manage")
	public String manage(Model model){
		return "union_login";
	}
	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}
}
