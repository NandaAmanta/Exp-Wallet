
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.global;

/**
 *
 * @author I Putu Nanda Amanta
 */
public class Route {

    /**
     * Base API routes
     */
    public static final String API_V1 = "/api/v1";

    /**
     * User routes
     */
    public static final String USER = "/users";

    /**
     * Auth routes
     */
    public static final String AUTH = "/auth";
    public static final String SIGNUP_VERIFICATION = "/signup/verification";
    public static final String LOGIN = "/login";
    public static final String SIGNUP = "/signup";

    /**
     * Balance routes
     */
    public static final String BALANCE = "/balances";
    public static final String TOPUP = "/topup";

    /**
     * Id parameter request
     */
    public static final String ID = "/{id}";

}
