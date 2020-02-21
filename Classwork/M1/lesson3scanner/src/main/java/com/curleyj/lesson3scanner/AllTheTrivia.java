package com.curleyj.lesson3scanner;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class AllTheTrivia {
    public static void main(String[] args) {
        String terabyte;
        String planet;
        String volcano;
        String element;
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("1,024 Gigabytes is equal to one what?");
        terabyte = myScanner.nextLine();
        
        System.out.println("In our Solar System, which is the only planet that rotates clockwise?");
        planet = myScanner.nextLine();
        
        System.out.println("The largest volcano ever discovered in our Solar System is located on which planet?");
        volcano = myScanner.nextLine();
        
        System.out.println("What is the most abunant element in the earth's atmosphere?");
        element = myScanner.nextLine();
        
        System.out.println("Wow, 1,024 Gigabytes is a " + volcano + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on " + terabyte + "!");
        System.out.println("That's amazing that " + planet + " is the most abundant element in the atmosphere...");
        System.out.println(element + " is the only planet that rotates clockwise, neat!");
        
        
    }
}
