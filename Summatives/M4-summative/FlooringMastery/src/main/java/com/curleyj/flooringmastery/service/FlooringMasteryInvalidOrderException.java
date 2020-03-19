/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

/**
 *
 * @author Jake
 */
public class FlooringMasteryInvalidOrderException extends Exception{

    public FlooringMasteryInvalidOrderException(String message) {
        super(message);
    }

    public FlooringMasteryInvalidOrderException(String message,
            Throwable cause) {
        super(message, cause);
    }

}

