/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.applications.response.v1;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author ASUS ROG
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseError extends Response {

    /**
     * Set default value ok to be false
     */
    private boolean ok = false;

    /**
     * custom error code
     */
    private String errorCode;

    /**
     * Errors Object/message
     */
    private Object errors = new ArrayList<>();
}
