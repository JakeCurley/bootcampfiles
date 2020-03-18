package com.curleyj.refactor;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class refactorInterestCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What is the annual interest rate? ");
        float rate = sc.nextFloat();
        
        System.out.println("What is the inital amount of principle? ");
        float principle = sc.nextFloat();
        
        System.out.println("Number of years the money will stay in the fund?");
        int yearsIn = sc.nextInt();
        
        System.out.println("How many times a year does it compound? ");
        int period = sc.nextInt();
       
        Money myMoney = new Money();
        myMoney.mon(rate, principle, yearsIn,period);
    }
}
