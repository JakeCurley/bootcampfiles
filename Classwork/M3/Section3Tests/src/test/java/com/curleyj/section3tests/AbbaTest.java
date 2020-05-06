/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section3tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jake
 */
public class AbbaTest {
    
    public AbbaTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of abba method, of class Abba.
     */
    @Test
    public void testAbbaHiBye() {
        Abba a = new Abba();
        String correct = "HiByeByeHi";
        String result = a.abba("Hi", "Bye");
        
        assertEquals(result, correct);
    }
    
    public void testAbbaYoAlice() {
        Abba a = new Abba();
        String correct = "YoAliceAliceYo";
        String result = a.abba("Yo", "Alice");
        
        assertEquals(result, correct);
    }
    
    public void testAbbaWhatUpUpWhat() {
        Abba a = new Abba();
        String correct = "WhatUpUpWhat";
        String result = a.abba("What", "Up");
        
        assertEquals(result, correct);
    }
    
}
