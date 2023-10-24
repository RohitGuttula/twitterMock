package com.example.twitter.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationObject {

    private String firstName;
    private String lastName;
    private String email;
    private Date DOB;
}
