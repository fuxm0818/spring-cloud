/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package cloud.simple.service;

import cloud.simple.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    //这个服务名称就是在服务提供端 cloud-simple-service 中 spring.application.name所配置的名字，这个名字在服务启动时连同它的 IP 和端口号都注册到了 EurekaServer
    final String SERVICE_NAME = "cloud-simple-service";

    //	断路器的基本作用就是@HystrixCommand注解的方法失败后，系统将自动切换到 fallbackMethod 方法执行
    @HystrixCommand(fallbackMethod = "fallbackSearchAll")
    public List<User> readUserInfo() {//请求后台服务项目，后去数据
        return restTemplate.getForObject("http://" + SERVICE_NAME + "/user", List.class);
        //return feignUserService.readUserInfo();
    }

    private List<User> fallbackSearchAll() {
        System.out.println("HystrixCommand fallbackMethod handle!");
        List<User> ls = new ArrayList<User>();
        User user = new User();
        user.setUsername("TestHystrixCommand");
        ls.add(user);
        return ls;
    }
}
