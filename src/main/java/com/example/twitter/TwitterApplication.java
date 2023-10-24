package com.example.twitter;

import com.example.twitter.Models.ApplicationUser;
import com.example.twitter.Models.Role;
import com.example.twitter.Service.UserService;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run(RoleRepository roleRepository, UserService userService){
//           return args -> {
//               roleRepository.save(new Role(1,"USER"));
//               ApplicationUser user=new ApplicationUser();
//               user.setFirstName("Guttula");
//               user.setLastName("Rohit");
//               userService.registerUser(user);
//           };
//    }

}
