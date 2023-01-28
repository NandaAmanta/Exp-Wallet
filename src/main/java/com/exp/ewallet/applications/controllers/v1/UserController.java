/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.controllers.v1;

import com.exp.ewallet.applications.response.v1.Response;
import com.exp.ewallet.applications.response.v1.ResponseSuccess;
import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.global.Route;
import com.exp.ewallet.presist.useCases.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS ROG
 */
@RestController
@RequestMapping(Route.API_V1 + Route.USER)
public class UserController {

    /**
     * Inject Dependency UserUseCase
     */
    @Autowired
    private UserUseCase userUseCase;

    /**
     * Response Success Object
     */
    private final ResponseSuccess response = new ResponseSuccess();

    /**
     * Get User Detail by Id
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(Route.ID)
    public ResponseEntity<Response> getUserDetail(@PathVariable Long id) {
        UserDetail userDetail = userUseCase.getUserDetailById(id);
        response.setData(userDetail);
        response.setMessage("Success get user detail");
        response.setStatus(HttpStatus.OK.toString());
        return ResponseEntity.ok(response);
    }
    
}
