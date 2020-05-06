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
public class MakePiTest {
    
    MakePi mp = new MakePi();
    
    public MakePiTest() {
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
        String pi = "";
        String aOld = "";
        String correct = "314";
        int n = 3;
        int[] x = mp.makePi(n);
        
        for (int i = 0; i < x.length; i++) {
            int z = x[i];
            String a = Integer.toString(z);
            pi = aOld + a;
            aOld = pi;
        }
        System.out.println(pi);
        assertEquals(pi, correct);
    }
    
    @Test
    public void testSomeMethod1() {
        String pi = "";
        String aOld = "";
        String correct = "314159";
        int n = 6;
        int[] x = mp.makePi(n);
        
        for (int i = 0; i < x.length; i++) {
            int z = x[i];
            String a = Integer.toString(z);
            pi = aOld + a;
            aOld = pi;
        }
        System.out.println(pi);
        assertEquals(pi, correct);
    }
    
    @Test
    public void testSomeMethod2() {
        String pi = "";
        String aOld = "";
        String correct = "3";
        int n = 1;
        int[] x = mp.makePi(n);
        
        for (int i = 0; i < x.length; i++) {
            int z = x[i];
            String a = Integer.toString(z);
            pi = aOld + a;
            aOld = pi;
        }
        System.out.println(pi);
        assertEquals(pi, correct);
    }
    
}
