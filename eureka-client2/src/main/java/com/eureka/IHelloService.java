package com.eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IHelloService
 * 配置服务提供者：single-provider 是服务提供者的 application.name
 */
@FeignClient("single-provider")
public interface IHelloService {

    @RequestMapping(value = "/hello")
    String hello();

    @RequestMapping(value = "nice")
    String nice();
}
