package com.curleyj.leson7forloops;

/**
 *
 * @author Jake
 */
public class ForandTwentyBlackbirds {
    public static void main(String[] args) {
        int birdsInPie = 0;
        for (int i = 0; i < 20; i++) {
            System.out.println("Blackbird # " + i + " goes into the pie!");
            birdsInPie++;
        }
        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie fill!");
    }
}
