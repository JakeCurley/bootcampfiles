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
public class CanHazTableTest {
    CanHazTable can = new CanHazTable();
    public CanHazTableTest() {
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
     * Test of canHazTable method, of class CanHazTable.
     */
    @Test
    public void testCanHaz5Table10() {
        int result = can.canHazTable(5, 10);
        
        assertEquals(result, 2);
        
    }
    
    public void testCanHaz5Table2() {
        int result = can.canHazTable(5, 2);
        
        assertEquals(result, 0);
    }
    
    public void testCanHaz5Table5() {
        int result = can.canHazTable(5, 5);
        
        assertEquals(result, 1);
    }
}
