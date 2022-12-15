/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.response.v1;

import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
public class Response {

    /**
     * Status success (true) or error (false)
     */
    private boolean ok;

    /**
     * HTTP Status Code
     */
    private String status;

    /**
     * Server Message
     */
    private String message;
}
