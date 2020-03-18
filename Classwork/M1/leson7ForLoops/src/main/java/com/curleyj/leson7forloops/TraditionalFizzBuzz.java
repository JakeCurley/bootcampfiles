package com.curleyj.leson7forloops;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many fizzing and buzzing units do you need in your life?");
        String sInput = sc.nextLine();
        int input = Integer.parseInt(sInput);
        int counter = 0;
        
        for (int i = 0; i >= 0; i++) {
            if ((i % 3 == 0) && (i % 5 == 0) && (i != 0)) {
                System.out.println("fizz buzz");
                counter++;
            }
            else if (((i % 3 == 0) || (i % 5 == 0)) && (i != 0)) {
                if (i % 3 == 0) {
                    System.out.println("fizz");
                }
                else if (i % 5 == 0) {
                    System.out.println("buzz");
                }
            counter++;
            }
            else {
                System.out.println(i);
            }
            if (counter == input) {
                System.out.println("TRADITION!");
                break;
            }
        }
    }
}
