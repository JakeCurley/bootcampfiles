package com.curleyj.lesson6dowhiles;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class LazyTeenager {
    public static void main(String[] args) {
        String room = "dirty";
        double timesTold = .05;
        Random nR = new Random();
        int loops = 0;
        do {
            loops++;
            System.out.println("Clean your room!! (x" + loops + ")");
            timesTold += .05;
            
            if (loops > 15) {
                System.out.println("YOU'RE GROUNDED AND I'M TAKING YOUR XBOX");
                break;
            }
            
            double num = nR.nextDouble();
            if (num + timesTold > 1)
            {
                System.out.println("FINE! I'LL CLEAN MY ROOM BUT I REFUSE TO EAT MY PEAS");
                room = "clean";
            }
        } while (room.equals("dirty"));
    }
}
