package com.wp.miaoshaproject;

import com.wp.miaoshaproject.dao.UserDOMapper;
import com.wp.miaoshaproject.dataobject.UserDO;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.wp.miaoshaproject"} )
@MapperScan("com.wp.miaoshaproject.dao")//扫描mapper
@RestController
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);
    }
}
