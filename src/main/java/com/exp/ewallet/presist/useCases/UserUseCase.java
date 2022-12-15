/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.presist.models.User;
import com.exp.ewallet.presist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author ASUS ROG
 */
public class UserUseCase implements UserDetailsService {

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
                .password(user.getPassword()).build();
        return userDetails;
    }

    /**
     * Get / load user detail by id
     *
     * @param id
     * @return UserDetail
     */
    public UserDetail getUserDetailById(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow();
        UserDetail userDetail = UserDetail.fromEntity(user);
        return userDetail;
    }
    
    

}
