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
public class MakeTagsTest {
    
    MakeTags mt = new MakeTags();
    public MakeTagsTest() {
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
        String correct = "<i>Yay</i>";
        String tag = "i";
        String content = "Yay";
        
        String result = mt.makeTags(tag, content);
        System.out.println(result);
        assertEquals(correct, result);
    }
    
    @Test
    public void testSomeMethod1() {
        String correct = "<i>Hello</i>";
        String tag = "i";
        String content = "Hello";
        
        String result = mt.makeTags(tag, content);
        System.out.println(result);
        assertEquals(correct, result);
    }
    
    @Test
    public void testSomeMethod2() {
        String correct = "<cite>Yay</cite>";
        String tag = "cite";
        String content = "Yay";
        
        String result = mt.makeTags(tag, content);
        System.out.println(result);
        assertEquals(correct, result);
    }
    
    @Test
    public void testSomeMethod3() {
        String correct = "<cite>This is a long test string</cite>";
        String tag = "cite";
        String content = "This is a long test string";
        
        String result = mt.makeTags(tag, content);
        System.out.println(result);
        assertEquals(correct, result);
    }
    
}
