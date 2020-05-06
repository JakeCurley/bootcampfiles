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
public class SleepingInTest {
    
    SleepingIn si = new SleepingIn();
    
    public SleepingInTest() {
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
    public void testWeekdayVacation() {
        
        boolean isWeekday = true;
        boolean isVacation = true;
        
        assertTrue(si.canSleepIn(isWeekday, isVacation));
        
    }
    @Test
    public void testWeekendVacation() {
        
        boolean isWeekday = false;
        boolean isVacation = true;
        
        assertTrue(si.canSleepIn(isWeekday, isVacation));
        
    }
    
    @Test
    public void testWeekdayNotVacation() {
        
        boolean isWeekday = true;
        boolean isVacation = false;
        
        assertFalse(si.canSleepIn(isWeekday, isVacation));
        
    }
    
    @Test
    public void testWeekendNotVacation() {
        
        boolean isWeekday = false;
        boolean isVacation = false;
        
        assertTrue(si.canSleepIn(isWeekday, isVacation));
        
    }
    
}
