package com.training.backend.rest;

import com.training.backend.bean.LoginBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/basic-auth")
    public LoginBean doLogin(){
    	LoginBean login = new LoginBean();
    	login.setMessage("You are authenticated.");
        return login;
    }
}
