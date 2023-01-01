/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.useCases;

import com.exp.ewallet.applications.response.v1.verificationNumber.VerificationNumberDetail;
import com.exp.ewallet.exceptions.custom.ValidationNumberException;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.verificationNumber.VerificationNumber;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberStatus;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberType;
import com.exp.ewallet.presist.repositories.VerificationNumberRepository;
import com.exp.ewallet.utils.Generator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS ROG
 */
@Service
public class VerificationNumberUseCase {

    @Autowired
    private VerificationNumberRepository verificationNumberRepository;

    public VerificationNumberDetail generate(User user, VerificationNumberType type) {
        var verificationNumber = new VerificationNumber();
        verificationNumber.setType(type);
        verificationNumber.setUser(user);
        verificationNumber.setNumber(Generator.randomFourDigitsNumber());

        var savedVerificationNumber = this.verificationNumberRepository.save(verificationNumber);
        return VerificationNumberDetail.fromEntity(savedVerificationNumber);
    }

    public void validation(User user, VerificationNumberType type, String number) {
        Optional<VerificationNumber> result = this.verificationNumberRepository
                .findByUserIdAndNumberAndType(user.getId(), number, type);

        if (result.isEmpty()) {
            throw new ValidationNumberException("Validation Number is not found");
        }

        if (result.get().getStatus() != VerificationNumberStatus.AVAILABLE) {
            throw new ValidationNumberException("Validation Number is " + result.get().getStatus().toString());
        }

        if (result.get().getType() != type) {
            throw new ValidationNumberException("Invalid type Validation Number");
        }

        var verificationNumber = result.get();
        verificationNumber.setStatus(VerificationNumberStatus.UNAVAILABLE);
        
        this.verificationNumberRepository.save(verificationNumber);

    }
}
