package com.yang.petstore;


import com.github.pagehelper.PageHelper;
import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dataobject.UserDO;
import com.yang.petstore.response.CommonReturnType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.yang.petstore"})
@MapperScan("com.yang.petstore.dao")
@Controller
@EnableCaching //开启缓存


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
		return "adminlogin";
	}
	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}
