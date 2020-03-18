package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class HealthyHearts {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        String userAge;
        int age;
        int max;
        float targetMin;
        float targetMax;
        
        System.out.println("What is your age?");
        userAge = myScanner.nextLine();
        age = Integer.parseInt(userAge);
        
        max = 220 - age;
        targetMin = .5f * max;
        targetMax = .85f * max;
        
        System.out.println("Your maximum heart rate should be " + max + " beats per minute.");
        System.out.println("Your target heart rate is " + targetMin + " - " + targetMax + " beats per minute.");
        
        
        
        
    }
}
