package com.curleyj.lesson6dowhiles;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class MaybeItLovesMe {
    public static void main(String[] args) {
        
        Random ran = new Random();
        int petals = ran.nextInt(77) + 13;
        int sad = 0;
        while (petals > 0) {
            if (sad == 1) {
                System.out.println("It loves me NOT!");
                sad = 0;
                if (petals == 1) {
                    System.out.println("Doesn't love me :(");
                }
            } else {
                System.out.println("It LOVES me!");
                sad = 1;
                if (petals == 1) {
                    System.out.println("I knew it!  IT LOVES ME!");
                }
            }
            petals--;
        }
    }
}
