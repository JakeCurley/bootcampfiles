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
public class CommonEnd {
       // Given 2 arrays of ints, a and b, return true if they 
    // have the same first element or they have the same 
    // last element. Both arrays will be length 1 or more. 
    //
    // commonEnd({1, 2, 3}, {7, 3}) -> true
    // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
    // commonEnd({1, 2, 3}, {1, 3}) -> true
    public boolean commonEnd(int[] a, int[] b) {
        
        boolean check = false;
        
        if(b.length < a.length) {
            for(int i = 0; i < b.length-1; i++) {
                if ((a[i] == b[i]) || (a[a.length-1] == b[b.length-1])) {
                    check =  true;
                }
            }
        }
        else {
            for(int i = 0; i < a.length-1; i++) {
                if ((a[i] == b[i]) || (a[a.length-1] == b[b.length-1])) {
                    check =  true;
                }
            }
        }
        return check;
    }
}
