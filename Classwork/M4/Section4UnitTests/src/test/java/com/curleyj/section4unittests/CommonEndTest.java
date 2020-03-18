/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section4unittests;

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
public class CommonEndTest {
    
    CommonEnd ce = new CommonEnd();
    
    public CommonEndTest() {
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
        
        int[] a = {1, 2, 3};
        int[] b = {7, 3};
        
        boolean check = ce.commonEnd(a, b);
        
        assertTrue(check);
    }
    
    @Test
    public void testSomeMethod2() {
        
        int[] a = {1, 2, 3};
        int[] b = {7, 3, 2};
        
        boolean check = ce.commonEnd(a, b);
        
        assertFalse(check);
    }
    
    @Test
    public void testSomeMethod3() {
        
        int[] a = {1, 2, 3};
        int[] b = {1, 3};
        
        boolean check = ce.commonEnd(a, b);
        
        assertTrue(check);
    }
    
}
