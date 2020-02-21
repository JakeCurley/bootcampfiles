package com.curleyj.lesson6dowhiles;

/**
 *
 * @author Jake
 */
public class BewareTheKraken {
    public static void main(String[] args) {
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooooooooooo....! *SPLASH*");
        
        int depthDivedInFt = 0;
        
        while (depthDivedInFt < 36200) {
            System.out.println("So far, we've swum, " + depthDivedInFt + " feet");
            
            if (depthDivedInFt >= 20000) {
                System.out.println("Uhh, I think I see a Kraken, guys...");
                System.out.println("TIME TO GO!");
                break;
            }
            
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming, " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}
