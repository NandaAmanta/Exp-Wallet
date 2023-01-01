/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.user.enums.UserStatus;
import com.exp.ewallet.presist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS ROG
 */
@Service
public class UserUseCase implements UserDetailsService {

    /**
     * Dependency inject UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * get / load user by username
     *
     * @param username String
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow();
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .disabled(user.getStatus() == UserStatus.NOT_VERIFIED)
                .accountLocked(user.getStatus() == UserStatus.BANNED)
                .roles("USER")
                .password(user.getPassword()).build();
        
        return userDetails;
    }

    /**
     * Get / load user detail by id
     *
     * @param id
     * @return UserDetail
     */
    public UserDetail getUserDetailById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserDetail userDetail = UserDetail.fromEntity(user);
        return userDetail;
    }

}
