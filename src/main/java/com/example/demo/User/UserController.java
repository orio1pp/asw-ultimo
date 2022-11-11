package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("user/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("userlogin")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @GetMapping("user")
    public User getUser(@RequestParam String username){
        return userService.getUser(username);
    }

    @PostMapping("userk")
    public User setUser(@RequestBody User user){
        return userService.modifyUser(user);
    }

    @PostMapping("login")
    public void registerUser(@RequestParam String username){
        userService.insertUser(username);
    }
}
