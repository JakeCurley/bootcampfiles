package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class DontForgetToStoreIt {
    public static void main(String[] args) {
        int meaningOfLifeAndEverything = 42;
        double pi = 3.14159;
        String cheese, color;
        
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Give me pi to atleast 5 decimals: ");
        pi = inputReader.nextDouble();
        
        System.out.println("What is the meaning of life, the universe, & everything? ");
        meaningOfLifeAndEverything = inputReader.nextInt();
        
        System.out.println("What is your favorite kind of cheese?");
        cheese = inputReader.next();
        
        System.out.println("Do you like the color red or blue more?");
        color = inputReader.next();
        
        System.out.println("Ooh, " + color + " " + cheese + " sounds delicious!");
        System.out.println("The circumference of life is " +(2 * pi * meaningOfLifeAndEverything));
    }
}
