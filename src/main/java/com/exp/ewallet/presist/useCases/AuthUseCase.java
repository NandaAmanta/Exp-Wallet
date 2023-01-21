/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.request.v1.auth.LoginRequest;
import com.exp.ewallet.applications.request.v1.auth.SignupRequest;
import com.exp.ewallet.applications.request.v1.auth.SignupVerificationRequest;
import com.exp.ewallet.applications.response.v1.auth.TokenDetail;
import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.exceptions.custom.AccountException;
import com.exp.ewallet.exceptions.custom.WrongCredentialException;
import com.exp.ewallet.kernel.configs.PasswordEncoder;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.user.enums.UserStatus;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberType;
import com.exp.ewallet.presist.repositories.UserRepository;
import com.exp.ewallet.utils.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 *
 * @author ASUS ROG
 */
@Service
public class AuthUseCase {

    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private VerificationNumberUseCase verificationNumberUseCase;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Login Service
     *
     * @param request
     * @return
     */
    public TokenDetail login(LoginRequest request) {
        try {
            var userdetail = userUseCase.loadUserByUsername(request.getUsername());

            if (!userdetail.isEnabled()) {
                throw new AccountException("Account is still Not Verified");
            }

            if (!userdetail.isAccountNonLocked()) {
                throw new AccountException("Account is Banned");
            }

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

    /**
     * Sign up Service
     *
     * @param request
     * @return
     */
    public UserDetail signUp(SignupRequest request) {

        boolean isThereUserWithSameUsername = !userRepository
                .findAllByUserName(request.getUsername())
                .isEmpty();

        if (isThereUserWithSameUsername) {
            throw new AccountException("This username has been used by other user, try another username.");
        }

        boolean isThereUserWithSameEmail = !userRepository
                .findAllByEmail(request.getEmail())
                .isEmpty();

        if (isThereUserWithSameEmail) {
            throw new AccountException("This email has been used by other user, try another username.");
        }

        User newUser = request.toEntity();
        newUser.setPassword(passwordEncoder.getEncoder().encode(newUser.getPassword()));
        newUser = userRepository.save(newUser);
        var verificationNumberDetail = verificationNumberUseCase.generate(newUser, VerificationNumberType.SIGNUP);
        return UserDetail.fromEntity(newUser);
    }

    /**
     * Verification User Sign up
     *
     * @return
     */
    public UserDetail verificationSignUp(SignupVerificationRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        this.verificationNumberUseCase.validation(user, VerificationNumberType.SIGNUP, request.getNumber());
        user.setStatus(UserStatus.VERIFIED);
        var verifiedUser = userRepository.save(user);
        return UserDetail.fromEntity(user);
    }
}
