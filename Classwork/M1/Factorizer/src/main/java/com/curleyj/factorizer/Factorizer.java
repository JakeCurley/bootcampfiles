package com.curleyj.factorizer;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class Factorizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What number would you like to factor? ");
        int toFactor = sc.nextInt();
        System.out.println("The factors of " + toFactor + " are: ");
        factor(toFactor);
    }

    public static void factor(int a) {
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
