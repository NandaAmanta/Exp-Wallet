/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.response.v1.user;

import com.exp.ewallet.presist.models.user.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor()
public class UserDetail {

    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String phoneCodeCountry;

    /**
     * Constructor User Detail
     */
    public UserDetail() {
    }

    /**
     * Convert User Entity to User detail DTO
     *
     * @param user User
     * @return userDetail UserDetail
     */
    public static UserDetail fromEntity(User user) {
        UserDetail userDetail = new UserDetail(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getPhoneCountryCode()
        );
        return userDetail;
    }
}
