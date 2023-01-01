/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.exp.ewallet.presist.repositories;

import com.exp.ewallet.presist.models.verificationNumber.VerificationNumber;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS ROG
 */
@Repository
public interface VerificationNumberRepository extends JpaRepository<VerificationNumber, Long> {

    Optional<VerificationNumber> findByUserId(Long userId);

    Optional<VerificationNumber> findByUserIdAndNumberAndType(Long userId, String number, VerificationNumberType verificationNumberType);
}
