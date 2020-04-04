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
class Money {
    public void mon(float rate, float principle, float yearsIn, float period) {
        float currentBalance = 0;
        float yearInterest = 0;
        for (int i = 1; i <= yearsIn; i++) {
                int year = 2020 +i;
                System.out.println("Year: " + year);
                System.out.println("Principle at the start of the year: " + principle);
                float startPrinciple = principle;
                for (int j = 0; j < period; j++) {

                    currentBalance = principle * (1+((rate/period)/100));
                    //System.out.println(currentBalance);
                    principle = currentBalance;
                }
                yearInterest = currentBalance - startPrinciple;
                System.out.println("Total interest earned for the year: " + yearInterest);
                System.out.println("Principle at the end of the year: " + currentBalance);
            }
    }
}
