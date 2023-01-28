/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.presist.models.user;

import com.exp.ewallet.presist.models.user.enums.Role;
import com.exp.ewallet.presist.models.user.enums.UserStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author I Putu Nanda Amanta
 */
@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User unique name for the application
     */
    @Column(nullable = false,
            unique = true,
            name = "user_name")
    private String userName;

    /**
     * User full name detail data
     */
    @Column(nullable = false, name = "full_name")
    private String fullName;

    /**
     * User unique email
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * User password
     */
    @Column(nullable = false)
    private String password;

    /**
     * User password
     */
    @Column(nullable = true)
    private String pin;

    /**
     * User phone number
     */
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    /**
     * User phone country code
     */
    @Column(nullable = false, name = "phone_country_code")
    private String phoneCountryCode;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status = UserStatus.NOT_VERIFIED;
    
    /**
     * User role
     */
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

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
