/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.request.v1.auth.LoginRequest;
import com.exp.ewallet.applications.request.v1.auth.SignupRequest;
import com.exp.ewallet.applications.response.v1.auth.TokenDetail;
import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.exceptions.custom.WrongCredentialException;
import com.exp.ewallet.kernel.configs.PasswordEncoder;
import com.exp.ewallet.presist.models.User;
import com.exp.ewallet.presist.repositories.UserRepository;
import com.exp.ewallet.utils.JwtUtil;
import javax.security.auth.login.CredentialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS ROG
 */
@Service
public class AuthUseCase {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public TokenDetail login(LoginRequest request) {
        try {
            var userdetail = userUseCase.loadUserByUsername(request.getUsername());
            boolean isMatchPassword = passwordEncoder.getEncoder()
                    .matches(request.getPassword(), userdetail.getPassword());
            if (!isMatchPassword) {
                throw new WrongCredentialException();
            }
            String token = jwtUtil.generateToken(userdetail);
            return new TokenDetail(token);
        } catch (WrongCredentialException | UsernameNotFoundException ex) {
            throw new WrongCredentialException();
        }
    }

    public UserDetail signup(SignupRequest request) {
        User newUser = request.toEntity();
        newUser = userRepository.save(newUser);
        return UserDetail.fromEntity(newUser);
    }
}
