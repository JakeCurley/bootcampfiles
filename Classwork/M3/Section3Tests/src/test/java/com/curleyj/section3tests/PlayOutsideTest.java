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
public class PlayOutsideTest {
    
    PlayOutside pot = new PlayOutside();
    
    public PlayOutsideTest() {
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
        
        boolean summer = true;
        int temp = 59;    
        boolean result = pot.playOutside(temp, summer);    
        assertFalse(result);
    }
    
    @Test
    public void testSomeMethod2() {
        
        boolean summer = false;
        int temp = 70;     
        boolean result = pot.playOutside(temp, summer);     
        assertTrue(result);    
    }
    
    @Test
    public void testSomeMethod3() {
        
        boolean summer = false;
        int temp = 95;    
        boolean result = pot.playOutside(temp, summer);    
        assertFalse(result);    
    }
    
    @Test
    public void testSomeMethod4() {
        
        boolean summer = true;
        int temp = 95;    
        boolean result = pot.playOutside(temp, summer);    
        assertTrue(result);   
    }
    
    
    
}
