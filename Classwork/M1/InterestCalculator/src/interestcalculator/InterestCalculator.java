package interestcalculator;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class InterestCalculator {

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
        
        moneyMade(rate, principle, yearsIn, period);
       
            
    }   
    
    public static void moneyMade(float rate, float principle, float yearsIn, float period) {
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
