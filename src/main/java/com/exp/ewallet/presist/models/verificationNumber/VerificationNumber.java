/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.models.verificationNumber;

import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberType;
import com.exp.ewallet.presist.models.user.User;
import com.exp.ewallet.presist.models.verificationNumber.enums.VerificationNumberStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author I Putu Nanda Amanta
 */
@Entity
@Getter
@Setter
@Table(name = "verification_numbers")
public class VerificationNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Verification Number belongs to this user
     */
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The Verification number
     */
    @Column(name = "number", nullable = false)
    private String number;

    /**
     * Verification Number Type
     */
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private VerificationNumberType type;

    /**
     * Verification Number status
     */
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private VerificationNumberStatus status = VerificationNumberStatus.AVAILABLE;

    /**
     * data creation date time
     */
    @Column(name = "create_at")
    @CreationTimestamp
    private Date createAt;

    /**
     * data updated date time
     */
    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updateAt;
}
