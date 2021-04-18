package com.training.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.backend.dto.LoginBean;

@RestController
public class LoginController {

    @GetMapping("/basic-auth")
    public LoginBean doLogin(){
    	LoginBean login = new LoginBean();
    	login.setMessage("You are authenticated.");
        return login;
    }
}
