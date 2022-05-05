package com.cneport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zpw
 * @date 2022-05-03 18:14
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/hello")

    public @ResponseBody String sayHello(){
        return "hello world !!!";
    }
}
