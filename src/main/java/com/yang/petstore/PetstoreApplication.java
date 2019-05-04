package com.yang.petstore;


import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dataobject.UserDO;
import com.yang.petstore.response.CommonReturnType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

@SpringBootApplication(scanBasePackages = {"com.yang.petstore"})
@MapperScan("com.yang.petstore.dao")
@RestController
@EnableCaching //开启缓存

public class PetstoreApplication {

	@Autowired
	public UserDOMapper userDOMapper;

	@Resource
	private RedisTemplate<Serializable, Object> redisTemplate;


	@RequestMapping("/get")
	public CommonReturnType hello(){
		UserDO userDO = (UserDO) redisTemplate.opsForValue().get("user"+32);
		return CommonReturnType.create(userDO);
	}

	@RequestMapping("/p")
	@ResponseBody
	public String hell(){
		UserDO userDO = new UserDO();
		userDO.setAge(32);
		redisTemplate.opsForValue().set("user"+userDO.getAge(),userDO);
		return "hehe";
	}

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}
