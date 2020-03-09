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
public class StringTimesTest {
    
    private StringTimes st = new StringTimes();
    
    public StringTimesTest() {
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

    @Test
    public void testHi2() {
        String hi = "Hi";
        int n = 2;
        String right = "HiHi";
        
        assertEquals(right, st.stringTimes(hi, n));
    }
    
    public void testHi3() {
        String hi = "Hi";
        int n = 3;
        String right = "HiHiHi";
        
        assertEquals(right, st.stringTimes(hi, n));
    }
    
    public void testHi1() {
        String hi = "Hi";
        int n = 1;
        String right = "Hi";
        
        assertEquals(right, st.stringTimes(hi, n));
    }
    
    public void testHi5() {
        String hi = "Hi";
        int n = 5;
        String right = "HiHiHiHiHi";
        
        assertEquals(right, st.stringTimes(hi, n));
    }
    
}
