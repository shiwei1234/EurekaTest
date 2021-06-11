package com.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.HystrixProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EurekaService {
		
	
	    @Autowired
	    RestTemplate restTemplate;
	    @HystrixCommand(fallbackMethod = "getDefaultUser")
	    @HystrixProperty(name = "ExecutionIsolationStrategy",value = "SEMAPHORE" )
	    
	    public String getInfo(){
	        String message;
	            log.info("调用服务 doc");
	            message = restTemplate.getForObject("http:/adcb",String.class);
	            log.info("调用服务 返回信息："+message);
	        return message;
	    }
	    public String getDefaultUser() {
	        log.info("熔断，默认回调函数");
	        return "服务调用失败";
	    }
}
	
