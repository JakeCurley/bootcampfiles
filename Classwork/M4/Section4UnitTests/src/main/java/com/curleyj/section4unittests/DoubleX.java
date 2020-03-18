/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section4unittests;

/**
 *
 * @author Jake
 */
public class DoubleX {
        // Given a String, return true if the first instance 
    // of "x" in the String is immediately followed by 
    // another "x". 
    //
    // doubleX("axxbb") -> true
    // doubleX("axaxxax") -> false
    // doubleX("xxxxx") -> true
    public boolean doubleX(String str) {
        boolean check = true;
        for(int i = 0; i < str.length(); i++) {
            if (str.substring(i, i+1).equals("x")) {
                if (str.substring(i+1, i+2).equals("x")) {
                    check = true;
                    break;
                }
                else {
                check = false;
                break;
                }
            }
            
        }
        
        
        return check;
    }
}
