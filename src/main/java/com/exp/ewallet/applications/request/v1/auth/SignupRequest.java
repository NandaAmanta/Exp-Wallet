/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.request.v1.auth;

import com.exp.ewallet.presist.models.user.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SignupRequest {

    @NotNull(message = "Username can't be null")
    private String username;

    @NotNull(message = "Fullname can't be null")
    private String fullName;

    @NotNull(message = "Password can't be null")
    private String password;

    @NotNull(message = "Email can't be null")
    private String email;

    @NotNull(message = "Phone number can't be null")
    private String phoneNumber;

    @NotNull(message = "Phone country code can't be null")
    private String phoneCountryCode;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setUserName(username);
        user.setPhoneNumber(phoneNumber);
        user.setPhoneCountryCode(phoneCountryCode);
        return user;
    }
}
