/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.section3tests;

import static java.lang.Math.PI;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Jake
 */
public class MakePi {
    // Return an int array length n containing the first n digits of pi.
    //
    // makePi(3) -> {3, 1, 4}

    public int[] makePi(int n) {
        
        int[] x = new int[n];
        BigDecimal p = new BigDecimal(PI).movePointRight(n-1);
        p.setScale(n, RoundingMode.CEILING);
        String pi = p.toString();
        
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(pi.substring(i, i+1));
        }
        return x;
    }
}
