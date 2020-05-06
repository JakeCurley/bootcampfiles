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
public class GreatPartyTestTest {
    
    GreatPartyTest gp = new GreatPartyTest();
    
    public GreatPartyTestTest() {
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
    public void greatParty30False() {
        
        assertFalse(gp.greatParty(30, false));
        
        
    }
    
    @Test
    public void greatParty50False() {
      assertTrue(gp.greatParty(50, false));
    }
    
    @Test
    public void greatParty70True() {
        assertTrue(gp.greatParty(70, true));
    }
    
    @Test
    public void greatParty39False() {
        assertFalse(gp.greatParty(39, false));
    }
    
    @Test
    public void greatPraty71True() {
        assertTrue(gp.greatParty(71, true));
    }
    
    /**
     * Test of greatParty method, of class GreatPartyTest.
     */
    @Test
    public void testGreatParty() {
    }
}
