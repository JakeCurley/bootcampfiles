package com.curleyj.refactor;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class refactorFactorizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What number would you like to factor? ");
        int toFactor = sc.nextInt();
        System.out.println("The factors of " + toFactor + " are: ");
        Factor myFactor = new Factor();
        
        myFactor.fact(toFactor);
    }
}
