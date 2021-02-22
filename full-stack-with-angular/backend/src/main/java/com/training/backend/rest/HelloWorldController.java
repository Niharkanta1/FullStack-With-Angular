package com.training.backend.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.backend.dto.HelloWorldBean;
import com.training.backend.entity.Role;
import com.training.backend.entity.User;
import com.training.backend.repository.RoleRepository;
import com.training.backend.repository.UserRepository;

@RestController
public class HelloWorldController {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;

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
    
    @GetMapping("/saveData")
    public HelloWorldBean saveDataIntoDB(){
        HelloWorldBean bean = new HelloWorldBean();
        User user = new User();
        user.setUsername("nihar");
        user.setPassword("$2y$12$Q9Ar89w0MQRAJ8/XlfZNzuB8OXNy/wOzxGTjRb84ev/Dv/cLw7Xom");
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        role.setRoleDesc("User Role");
        roleRepo.save(role);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);
        bean.setMessage("Data Saved");
        return bean;
    }
}
