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
public class CaughtSpeedingTest {
    
    CaughtSpeeding cs = new CaughtSpeeding();
    
    public CaughtSpeedingTest() {
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
     * Test of caughtSpeeding method, of class CaughtSpeeding.
     */
    @Test
    public void testCaughtSpeeding() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 60;
        boolean isBirthday = false;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 0);
        
    }
    
    @Test
    public void testCaughtSpeeding2() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 65;
        boolean isBirthday = false;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 1);
        
    }
    
    @Test
    public void testCaughtSpeeding3() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 65;
        boolean isBirthday = true;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 0);
        
    }
    
    @Test
    public void testCaughtSpeeding4() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 81;
        boolean isBirthday = false;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 2);
        
    }
    
    @Test
    public void testCaughtSpeeding5() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 59;
        boolean isBirthday = false;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 0);
        
    }
    
    @Test
    public void testCaughtSpeeding6() {
        
        //caughtSpeeding(60, false) → 0
        // caughtSpeeding(65, false) → 1
        //caughtSpeeding(65, true) → 0
        
        int speed = 85;
        boolean isBirthday = true;
        
        int result = cs.caughtSpeeding(speed, isBirthday);
        
        assertEquals(result, 1);
        
    }
    
}
