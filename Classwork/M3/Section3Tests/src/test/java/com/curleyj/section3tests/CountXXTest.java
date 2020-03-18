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
public class CountXXTest {
    
    CountXX cx = new CountXX();
    
    public CountXXTest() {
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
     * Test of countXX method, of class CountXX.
     */
    @Test
    public void testCountXX() {
        
        String x = "abcxx";
        
        int result = cx.countXX(x);
        System.out.println(result);
        assertEquals(1, result);
    }
    public void testCountXX2() {
        
        String x = "xxx";
        
        int result = cx.countXX(x);
        System.out.println(result);
        assertEquals(2, result);
    }
    
    public void testCountXX3() {
        
        String x = "xxxx";
        
        int result = cx.countXX(x);
        System.out.println(result);
        assertEquals(3, result);
    }
    
    public void testCountXX4() {
        String x = "xxxoxoxoxoxoxxxxxoxoxoxoxxooxoxoxoxo";
        
        int result = cx.countXX(x);
        System.out.println(result);
        assertEquals(7, result);
    }
    
}
