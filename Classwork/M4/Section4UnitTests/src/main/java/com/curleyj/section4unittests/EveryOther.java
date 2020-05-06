/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section4unittests;

import java.util.Arrays;

/**
 *
 * @author Jake
 */
public class EveryOther {
        // Given a String, return a new String made of every other 
    // char starting with the first, so "Hello" yields "Hlo". 
    //
    // everyOther("Hello") -> "Hlo"
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"
    public String everyOther(String str) {
        String oldWord = "";
        String newWord = "";
        String[] a = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            a[i] = str.substring(i, i+1);
            newWord = oldWord + a[i];
            oldWord = newWord;
            System.out.println(a[i]);
            i++;
        }
        return newWord;
    }
}
