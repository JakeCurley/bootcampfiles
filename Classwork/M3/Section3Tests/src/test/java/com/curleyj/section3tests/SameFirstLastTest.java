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
public class SameFirstLastTest {
    
    SameFirstLast sfl = new SameFirstLast();
    
    public SameFirstLastTest() {
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
    public void testSomeMethod() {
        
        int[] x = {1, 2, 3};
        
        assertFalse(sfl.sameFirstLast(x));
        
    }
    
    public void testSomeMethod2() {
        
        int[] x = {1, 2, 3, 1};
        
        assertTrue(sfl.sameFirstLast(x));
        
    }
    
    public void testSomeMethod3() {
        
        int[] x = {1, 2, 1};
        
        assertTrue(sfl.sameFirstLast(x));
        
    }
    
    public void testSomeMethod4() {
        
        int[] x = {};
        
        assertFalse(sfl.sameFirstLast(x));
        
    }
    
}
