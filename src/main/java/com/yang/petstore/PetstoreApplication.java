package com.yang.petstore;

import com.yang.petstore.dao.UserDOMapper;
import com.yang.petstore.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.yang.petstore"})
@MapperScan("com.yang.petstore.dao")
@RestController
public class PetstoreApplication {

	@Autowired
	public UserDOMapper userDOMapper;


	@RequestMapping("/")
	public String hello(){
		UserDO userDO = userDOMapper.selectByPrimaryKey(1);
		if(userDO != null)
			return "haha";
		else
			return "xixi";
	}

	public static void main(String[] args) {
		SpringApplication.run(PetstoreApplication.class, args);
	}

}
