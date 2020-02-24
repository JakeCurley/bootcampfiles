package com.curleyj.lesson5random;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class CoinFlipper {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        boolean coin = randomizer.nextBoolean();
        System.out.println("Ready, Set, Flip....");
        
        if (coin == false) {
            System.out.println("You got tails.");
        }
        else if (coin == true) {
            System.out.println("You got heads.");
        }
    }
}
