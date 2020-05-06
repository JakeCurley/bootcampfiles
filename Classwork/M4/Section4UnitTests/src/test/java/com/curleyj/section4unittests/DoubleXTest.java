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
public class DoubleXTest {
    
    DoubleX dx = new DoubleX();
    
    public DoubleXTest() {
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
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "axxbb";
        
        boolean check = dx.doubleX(x);
        
        assertTrue(check);
    }
    
    public void testSomeMethod2() {
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "axaxxax";
        
        boolean check = dx.doubleX(x);
        
        assertFalse(check);
    }
    
    public void testSomeMethod3() {
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "xxxxx";
        
        boolean check = dx.doubleX(x);
        
        assertTrue(check);
    }
    public void testSomeMethod4() {
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "xdxdxdx";
        
        boolean check = dx.doubleX(x);
        
        assertFalse(check);
    }
    
    public void testSomeMethod5() {
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "asdsfdgsxxs";
        
        boolean check = dx.doubleX(x);
        
        assertTrue(check);
    }
    
    public void testSomeMethod6() {
        // doubleX("axxbb") -> true
        // doubleX("axaxxax") -> false
        // doubleX("xxxxx") -> true
    
        String x = "fdsgfdsgsfgsxs";
        
        boolean check = dx.doubleX(x);
        
        assertFalse(check);
    }
    
    
    
    
    
}
