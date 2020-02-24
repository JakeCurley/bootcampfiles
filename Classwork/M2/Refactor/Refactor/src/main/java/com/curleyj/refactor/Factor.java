/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.refactor;

/**
 *
 * @author Jake
 */
class Factor {  
    public void fact(int a) {
        int x = 0;
            int z = 0;
            for (int i = a; i > 0; i--) {
                for (int j = a; j > 0; j--) {
                    if (i*j == a) {
                        if (j == a) {
                            break;
                        }
                        else {
                            System.out.println(j);
                            z += j;
                            if (z == a) {
                                System.out.println(a + " is a perfect number.");
                            }
                        }
                        x++;
                    }
                }
            }
            if (x > 1) {
                System.out.println(a + " is not a prime number.");
            }
            else {
                System.out.println(a + " is a prime number");
            }
            System.out.println(a + " has " + x + " factors.");
    }    
}
