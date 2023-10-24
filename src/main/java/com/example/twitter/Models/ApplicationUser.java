package com.example.twitter.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.Set;


@Entity
@Table(name="users")
@Data
@ToString
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Integer userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="dob")
    private Date dateOfBirth;
    @Column(unique = true)
    private String userName;
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_Junction",
            joinColumns = {@JoinColumn (name= "user_id")},
            inverseJoinColumns = {@JoinColumn (name= "role_id")}
    )
    private Set<Role> authorities;

    private Boolean enabled;
    @Column(nullable = true)
    @JsonIgnore
    private Long verification;

    public ApplicationUser(){
        this.enabled=false;
    }




}
