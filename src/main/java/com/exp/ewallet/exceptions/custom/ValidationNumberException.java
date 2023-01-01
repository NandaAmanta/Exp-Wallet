/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.exceptions.custom;

/**
 *
 * @author ASUS ROG
 */
public class ValidationNumberException extends RuntimeException {

    public ValidationNumberException() {
    }

    public ValidationNumberException(String message) {
        super(message);
    }

    public ValidationNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationNumberException(Throwable cause) {
        super(cause);
    }

}
