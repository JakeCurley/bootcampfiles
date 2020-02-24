package com.curleyj.lesson6dowhiles;

/**
 *
 * @author Jake
 */
public class LovesMe {

    public static void main(String[] args) {
        int petals = 34;
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
