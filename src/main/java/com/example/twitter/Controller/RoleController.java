package com.example.twitter.Controller;

import com.example.twitter.Models.Role;
import com.example.twitter.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RoleController {

    public final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }
}
