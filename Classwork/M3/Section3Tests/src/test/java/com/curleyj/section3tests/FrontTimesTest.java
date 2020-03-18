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
public class FrontTimesTest {
    
    FrontTimes ft = new FrontTimes();
    public FrontTimesTest() {
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
     * Test of frontTimes method, of class FrontTimes.
     */
    @Test
    public void testFrontTimes2() {
        int n = 2;
        String correct = "ChoCho";
        String result = ft.frontTimes("Chocolate", n);
        
        assertEquals(correct, result);
    }
    
    public void testFrontTimes3() {
        int n = 3;
        String correct = "ChoChoCho";
        String result = ft.frontTimes("Chocolate", n);
        
        assertEquals(correct, result);
    }
    
    public void testFrontTimesAbc3() {
        int n = 3;
        String correct = "AbcAbcAbc";
        String result = ft.frontTimes("Abc", n);
        
        assertEquals(correct, result);
    }
    
    public void testFrontTimesChicago7() {
        int n = 7;
        String correct = "ChiChiChiChiChiChiChi";
        String result = ft.frontTimes("Chicago", n);
        
        assertEquals(correct, result);
    }
    public void testFrontTimesCh7() {
        int n = 7;
        String correct = "ChChChChChChCh";
        String result = ft.frontTimes("Ch", n);
        
        assertEquals(correct, result);
    }
    
}
