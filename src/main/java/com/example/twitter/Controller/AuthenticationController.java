package com.example.twitter.Controller;

import com.example.twitter.Models.ApplicationUser;
import com.example.twitter.Models.RegistrationObject;
import com.example.twitter.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationObject ro){
        return userService.registerUser(ro);
    }
    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String,String> body){
        String userName= body.get("userName");
        String phone=body.get("phone");
        ApplicationUser user=userService.getUserByUserName(userName);
        user.setPhone(phone);
        return userService.updateUser(user);

    }
}
