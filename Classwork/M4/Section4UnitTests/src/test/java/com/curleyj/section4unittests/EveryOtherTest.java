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
public class EveryOtherTest {
    
    EveryOther eo = new EveryOther();
    
    public EveryOtherTest() {
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
    
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {  
        String word = "Hello";
        String result = eo.everyOther(word);
        assertEquals("Hlo", result);   
    }
    
    @Test
    public void testSomeMethod2() {  
        String word = "Hi";
        String result = eo.everyOther(word);
        assertEquals("H", result);   
    }
    
    @Test
    public void testSomeMethod3() {  
        String word = "Heeololeo";
        String result = eo.everyOther(word);
        assertEquals("Hello", result);   
    }
    
}
