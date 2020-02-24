package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class DoItBetter {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String userMiles;
        String userHotdogs;
        String userLanguages;
        
        int miles, hotdogs, languages, total;
        
        System.out.println("How many miles can you run?");
        userMiles = myScanner.nextLine();
        miles = Integer.parseInt(userMiles);
        total = miles * 2 + 1;
        System.out.println("That's nothing, I can run " + total + " miles.");
        
        System.out.println("How many hotdogs can you eat?");
        userHotdogs = myScanner.nextLine();
        hotdogs = Integer.parseInt(userHotdogs);
        total = hotdogs * 2 + 1;
        System.out.println("That's nothing, I can eat " + total + " hotdogs." );
        
        
        System.out.println("How many languages do you know?");
        userLanguages = myScanner.nextLine();
        languages = Integer.parseInt(userLanguages);
        total = languages * 2 + 1;
        System.out.println("That's nothing I can speak, " + total + " languages.");
        
                
        
    }
}
