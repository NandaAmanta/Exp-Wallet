/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.response.v1.verificationNumber;

import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.verificationNumber.VerificationNumber;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor()
public class VerificationNumberDetail {

    private String number;

    private VerificationNumberType type;

    private UserDetail user;

    public static VerificationNumberDetail fromEntity(VerificationNumber entity) {
        var userDetail = UserDetail.fromEntity(entity.getUser());
        var dto = new VerificationNumberDetail(
                entity.getNumber(),
                entity.getType(),
                userDetail);
        return dto;
    }
    
}
