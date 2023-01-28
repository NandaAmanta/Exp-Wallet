/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.request.v1.admin;

import com.exp.ewallet.presist.models.user.enums.UserStatus;
import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
public class UserStatusUpdateRequest {
 
    private UserStatus status;
    
}
