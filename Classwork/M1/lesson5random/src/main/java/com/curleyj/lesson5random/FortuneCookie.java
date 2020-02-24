package com.curleyj.lesson5random;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class FortuneCookie {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        int x = randomizer.nextInt(10);
        
        if (x == 0) {
            System.out.println("Your Greek Fortune : Those aren't the droids you're looking for.");
        }
        else if (x == 1) {
            System.out.println("Your Greek Fortune : Never go in against a Sicilian when death is on the line.");
        }
        else if (x == 2) {
            System.out.println("Your Greek Fortune : Goonies never say die.");
        }
        else if (x == 3) {
            System.out.println("Your Greek Fortune : With great power there must also be great responsibility.");
        }
        else if (x == 4) {
            System.out.println("Your Greek Fortune : Never argue with the data.");
        }
        else if (x == 5) {
            System.out.println("Your Greek Fortune : Try not. Do, or do not.  There is no try.");
        }
        else if (x == 6) {
            System.out.println("Your Greek Fortune : You are a leaf on the wind, watch how you soar.");
        }
        else if (x == 7) {
            System.out.println("Your Greek Fortune : Do absolutely nothing, and it will be everything that you thought it could be.");
        }
        else if (x == 8) {
            System.out.println("Your Greek Fortune : Kneel before Zod.");
        }
        else if (x == 9) {
            System.out.println("Your Greek Fortune : Make it so.");
        }
    }
}
