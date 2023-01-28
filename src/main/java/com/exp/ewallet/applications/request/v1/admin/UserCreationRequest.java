/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.request.v1.admin;

import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.user.enums.Role;
import com.exp.ewallet.presist.models.user.enums.UserStatus;
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
public class UserCreationRequest {

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

    @NotNull(message = "role can't be null")
    private Role role;

    @NotNull(message = "Status can't be null")
    private UserStatus status;

    public User toEntity() {
        User user = new User();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setUserName(username);
        user.setPhoneNumber(phoneNumber);
        user.setPhoneCountryCode(phoneCountryCode);
        user.setRole(role);
        user.setStatus(status);
        return user;
    }
}
