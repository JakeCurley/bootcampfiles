package com.curleyj.lesson9arrays;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class HiddenNuts {
    public static void main(String[] args) {
        String[] hidingSpots = new String[100];
        Random squirrel = new Random();
        hidingSpots[squirrel.nextInt(hidingSpots.length)] = "Nut";
        System.out.println("The nut has been hidden ...");
        
        for (int i = 0; i < hidingSpots.length; i++) {
            if(hidingSpots[i] == "Nut") {
                System.out.println("The Nut is in # " + i);
            }
            else {
                System.out.println("It's not in # " + i);
            }    
        }
    }
}
