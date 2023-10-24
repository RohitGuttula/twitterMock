package com.example.twitter.repository;

import com.example.twitter.Models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {

       Optional<ApplicationUser> findByUserName(String userName);
}
