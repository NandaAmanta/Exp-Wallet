/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.request.v1.admin.UserCreationRequest;
import com.exp.ewallet.applications.request.v1.admin.UserStatusUpdateRequest;
import com.exp.ewallet.applications.response.v1.user.UserDetail;
import com.exp.ewallet.exceptions.custom.AccountException;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.user.enums.UserStatus;
import com.exp.ewallet.presist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS ROG
 */
@Service
public class AdminUseCase {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create new user by admin
     *
     * @param data
     * @return
     */
    public UserDetail createUser(UserCreationRequest data) {
        boolean isThereUserWithSameUsername = !userRepository
                .findAllByUserName(data.getUsername())
                .isEmpty();

        if (isThereUserWithSameUsername) {
            throw new AccountException("This username has been used by other user, try another username.");
        }

        boolean isThereUserWithSameEmail = !userRepository
                .findAllByEmail(data.getEmail())
                .isEmpty();

        if (isThereUserWithSameEmail) {
            throw new AccountException("This email has been used by other user, try another username.");
        }

        User user = data.toEntity();
        User createdUser = userRepository.save(user);
        return UserDetail.fromEntity(createdUser);
    }

    /**
     * Ban user by admin
     *
     * @param userId
     * @return
     */
    public UserDetail updateStatusUser(Long userId, UserStatusUpdateRequest data) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setStatus(data.getStatus());
        User bannedUser = userRepository.save(user);
        return UserDetail.fromEntity(bannedUser);
    }

}
