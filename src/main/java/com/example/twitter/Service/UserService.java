package com.example.twitter.Service;

import com.example.twitter.ExceptionHandler.HttpStatusCodes;
import com.example.twitter.Exceptions.EmailAlreadyFoundException;
import com.example.twitter.Exceptions.UserDoesNotExistException;
import com.example.twitter.Models.ApplicationUser;
import com.example.twitter.Models.RegistrationObject;
import com.example.twitter.Models.Role;
import com.example.twitter.repository.RoleRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public ApplicationUser registerUser(RegistrationObject ro){
        ApplicationUser user=new ApplicationUser();
        user.setFirstName(ro.getFirstName());
        user.setLastName(ro.getLastName());
        user.setEmail(ro.getEmail());
        user.setDateOfBirth(ro.getDOB());
        String name=user.getFirstName()+user.getLastName();
        boolean nameTaken=true;
        String tempName="";
        while(nameTaken) {
            tempName = generateRandomName(name);
            if (userRepository.findByUserName(tempName).isEmpty()) {
                nameTaken = false;
            }
        }
        user.setUserName(tempName);
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findByAuthority("USER").get());
        user.setAuthorities(roles);
        try{
            return userRepository.save(user);
        }
        catch (Exception e){
            throw new EmailAlreadyFoundException
                    (HttpStatusCodes.BAD_REQUEST,"Email Already Exits for User");
        }
    }

    private String generateRandomName(String name) {
        long min = 100_000_000L; // 100,000,000 (the smallest 9-digit number)
        long max = 999_999_999L; // 999,999,999 (the largest 9-digit number)

        long randomNineDigitNumber = (long) (Math.random() * (max - min + 1)) + min;
        return name+randomNineDigitNumber;
    }

    public ApplicationUser getUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserDoesNotExistException
                        (HttpStatusCodes.NOT_FOUND, "UserName is not found"));
    }
    public ApplicationUser updateUser(ApplicationUser user){
        try{
            return userRepository.save(user);
        }
        catch (Exception e){
            throw new EmailAlreadyFoundException
                    (HttpStatusCodes.BAD_REQUEST,"Email Already Exits for User");
        }
    }

    public void createEmailVerification(String userName) {
        ApplicationUser user=userRepository.findByUserName(userName)
                .orElseThrow(()->new UserDoesNotExistException
                        (HttpStatusCodes.NOT_FOUND,"UserName is not found"));
        user.setVerification(generateVerificationNumber());
        userRepository.save(user);
    }
    private Long generateVerificationNumber(){
        return (long) Math.floor(Math.random()+100_000_000);
    }
}
