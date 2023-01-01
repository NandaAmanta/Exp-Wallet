/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exp.ewallet.utils;

import java.util.Random;

/**
 *
 * @author ASUS ROG
 */
public class Generator {

    public static String randomFourDigitsNumber() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

}
