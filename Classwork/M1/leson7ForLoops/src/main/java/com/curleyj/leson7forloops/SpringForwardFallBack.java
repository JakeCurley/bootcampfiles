package com.curleyj.leson7forloops;

/**
 *
 * @author Jake
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {
        System.out.println("It's spring...!");
        for (int i = -10; i < 0; i++) {  //starts at i = 0 and ends at i = 10
            System.out.print(i*-1 + ", ");
        }
        System.out.println("\nOh no, it's fall...");
        for (int i = 10; i > 0; i--) { //starts at i = 10 and ends at i = 0;
            System.out.print(i + ", ");
        }
            
    }
}
