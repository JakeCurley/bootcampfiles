package com.curleyj.lesson8methods;

import java.util.Random;

/**
 *
 * @author Jake
 */
public class BarelyControlledChaos {
    public static void main(String[] args) {
        String color = option();
        String animal = option2();
        String colorAgain = option();
        int weight = rNum(5, 200);
        int distance = rNum(10, 20);
        int number = rNum(10000, 20000);
        int time = rNum(2, 6);
        
        System.out.println("Once, when I was very small..");
        System.out.println("I was chased by a " + color + ", " + weight + " lb " + " miniature " + animal + " for over " + distance + " miles!");
        System.out.println("I had to hide in a field of over " + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");
        System.out.println("\nIt was QUITE the experience, " + "let me tell you!");
    }
    
    public static String option() {
        Random rg = new Random();
        int newC = rg.nextInt(4);
        String option = "";
        switch (newC) {
            case 0:
                option = "blue";
                break;
            case 1:
                option = "red";
                break;
            case 2:
                option = "yellow";
                break;
            case 3:
                option = "purple";
                break;
            case 4:
                option = "green";      
        }
        return option;
    }
    public static String option2() {
        Random rg = new Random();
        int newC = rg.nextInt(4);
        String option = "";
        switch (newC) {
            case 0:
                option = "cat";
                break;
            case 1:
                option = "dog";
                break;
            case 2:
                option = "bird";
                break;
            case 3:
                option = "monkey";
                break;
            case 4:
                option = "sloth";      
        }
        return option;
    }
    public static int rNum(int a, int b) {
        Random nr = new Random();
        
        int num = nr.nextInt(b + 1 - a) + a;
        
        return num;
    }
 
}
