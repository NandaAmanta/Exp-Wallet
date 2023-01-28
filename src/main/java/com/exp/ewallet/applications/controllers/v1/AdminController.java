/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.controllers.v1;

import com.exp.ewallet.applications.request.v1.admin.UserCreationRequest;
import com.exp.ewallet.applications.request.v1.admin.UserStatusUpdateRequest;
import com.exp.ewallet.applications.response.v1.Response;
import com.exp.ewallet.applications.response.v1.ResponseSuccess;
import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.global.Route;
import com.exp.ewallet.presist.useCases.AdminUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS ROG
 */
@RestController
@RequestMapping(Route.API_V1 + Route.ADMIN)
public class AdminController {

    @Autowired
    private AdminUseCase adminUseCase;

    private final ResponseSuccess response = new ResponseSuccess();

    /**
     * Create new user by admin
     *
     * @param request
     * @return
     */
    public ResponseEntity<Response> createUser(@RequestBody UserCreationRequest request) {
        UserDetail userDetail = adminUseCase.createUser(request);
        response.setData(userDetail);
        response.setMessage("Success create new user via admin");
        response.setOk(true);
        response.setStatus(HttpStatus.CREATED.toString());
        return ResponseEntity.ok(this.response);
    }

    /**
     * Update user status, they can be Banned, verified, and etc
     *
     * @param request
     * @return
     */
    @RequestMapping(Route.USER + Route.ID + "/status")
    public ResponseEntity<Response> updateUserStatus(@PathVariable Long id, @RequestBody UserStatusUpdateRequest request) {
        UserDetail userDetail = adminUseCase.updateStatusUser(id, request);
        response.setData(userDetail);
        response.setMessage("Success update user status via admin");
        response.setOk(true);
        response.setStatus(HttpStatus.CREATED.toString());
        return ResponseEntity.ok(new Response());
    }

}
