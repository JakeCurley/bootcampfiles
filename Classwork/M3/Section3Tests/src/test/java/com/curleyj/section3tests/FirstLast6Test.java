/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section3tests;

import java.lang.reflect.Array;
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
public class FirstLast6Test {
    
    FirstLast6 fl = new FirstLast6();
    public FirstLast6Test() {
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
        
        int[] x = {1, 2, 6};
        
        assertTrue(fl.firstLast6(x));
        
    }
    public void testSomeMethod2() {
        
        int[] x = {6, 1, 2, 3};
        
        assertTrue(fl.firstLast6(x));
        
    }
    public void testSomeMethod3() {
        
        int[] x = {13, 6, 1, 2, 3};
        
        assertFalse(fl.firstLast6(x));
        
    }
    public void testSomeMethod4() {
        
        int[] x = {6, 2, 6};
        
        assertTrue(fl.firstLast6(x));
        
    }
    
}
