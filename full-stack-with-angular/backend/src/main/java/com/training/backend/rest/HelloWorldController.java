package com.training.backend.rest;

import com.training.backend.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public HelloWorldBean getHelloWorld(){
        HelloWorldBean bean = new HelloWorldBean();
        bean.setMessage("Hello World !");
        return bean;
    }

    @GetMapping("/hello-world/{userName}")
    public HelloWorldBean getHelloWorldWithUser(@PathVariable String userName){
        HelloWorldBean bean = new HelloWorldBean();
        bean.setMessage("Hello World ! Hi " + userName);
        return bean;
    }
}
