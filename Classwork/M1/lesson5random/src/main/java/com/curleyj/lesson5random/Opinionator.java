package com.curleyj.lesson5random;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class Opinionator {
    public static void main(String[] args) {
        Random randomizer = new Random();
        System.out.println("I can;t decide what animal I like the best.");
        System.out.println("I know! Random can decide FOR ME");
        
        int x = randomizer.nextInt(6); // meeds to be 6 in order for 5 to be possible
        System.out.println("The number we chose was: " + x);
        
        if (x == 0) {
            System.out.println("Llamas are the best!");
        }
        else if (x == 1) {
            System.out.println("Woolly Mammoths are definitely the best!");
        }
        else if (x == 3) {
            System.out.println("Sharks are the greatest, they have their own week!");
        }
        else if (x == 4) {
            System.out.println("Cockatoos are just so awesome");
        }
        else if (x == 5) {
            System.out.println("Have you ever met a Mole-Rat?  They're GREAT!");
        }
        
        System.out.println("Thanks Random, maybe you're the best!");
    }
}
