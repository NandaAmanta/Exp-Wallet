/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.controllers.v1;

import com.exp.ewallet.applications.request.v1.auth.LoginRequest;
import com.exp.ewallet.applications.request.v1.auth.SignupRequest;
import com.exp.ewallet.applications.request.v1.auth.SignupVerificationRequest;
import com.exp.ewallet.applications.response.v1.Response;
import com.exp.ewallet.applications.response.v1.ResponseSuccess;
import com.exp.ewallet.global.Route;
import com.exp.ewallet.presist.useCases.AuthUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS ROG
 */
@RestController
@RequestMapping(Route.API_V1 + Route.AUTH)
public class AuthController {

    @Autowired
    private AuthUseCase authUseCase;

    /**
     * Response Success Object
     */
    private final ResponseSuccess response = new ResponseSuccess();

    @PostMapping(Route.LOGIN)
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
        var userDetail = authUseCase.login(request);
        response.setData(userDetail);
        response.setMessage("Success Loggin");
        response.setStatus(HttpStatus.OK.toString());
        return ResponseEntity.ok(this.response);
    }

    @PostMapping(Route.SIGNUP)
    public ResponseEntity<Response> signup(@RequestBody SignupRequest request) {
        var userDetail = authUseCase.signUp(request);
        response.setData(userDetail);
        response.setMessage("Success Sign up new User");
        response.setStatus(HttpStatus.CREATED.toString());
        return ResponseEntity.ok(this.response);
    }

    @PostMapping(Route.SIGNUP_VERIFICATION + Route.ID)
    public ResponseEntity<Response> signupVerification(@PathVariable Long id, @RequestBody SignupVerificationRequest request) {
        var userDetail = authUseCase.verificationSignUp(request, id);
        response.setData(userDetail);
        response.setMessage("Success verificate user");
        response.setStatus(HttpStatus.ACCEPTED.toString());
        return ResponseEntity.ok(this.response);
    }

}
