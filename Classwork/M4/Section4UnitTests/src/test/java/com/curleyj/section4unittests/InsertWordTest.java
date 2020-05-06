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
public class InsertWordTest {
    
    InsertWord iw = new InsertWord();
    
    public InsertWordTest() {
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
     * Test of insertWord method, of class InsertWord.
     */
    @Test
    public void testInsertWord() {
    String out = "<<>>";
    String word = "Yay";
    
    String result = iw.insertWord(out, word);
    assertEquals(result, "<<Yay>>");
    
    }
    
    @Test
    public void testSomeMethod2() {
        String out = "<<>>";
        String word = "WooHoo";
        
        String result = iw.insertWord(out, word);
        
        assertEquals(result, "<<WooHoo>>");
    }
    
    @Test
    public void testSomeMethod3() {
        String out = "[[]]";
        String word = "word";
        
        String result = iw.insertWord(out, word);
        
        assertEquals(result, "[[word]]");
    }
    
}
