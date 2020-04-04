package com.curleyj.luckysevens;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class LuckySevens {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random nr = new Random();
        
        System.out.println("How many dollars do you have to bet? ");
        int bet = sc.nextInt();
        int rolls = 0;
        int max = 0;
        int rollsAtMax = 0;
        do {
            int die1 = nr.nextInt (6) + 1;
            int die2 = nr.nextInt(6) + 1;
            rolls++;
            
            if ((die1 + die2) == 7) {
                bet += 4;
            }
            
            else {
                bet--;
            }
            
            if (bet >= max) {
                max = bet;
                rollsAtMax = rolls;
            }
            
        } while (bet > 0);
        
        System.out.println("You are broke after " + rolls + " rolls.");
        System.out.println("You should have quit after " + rollsAtMax + " when you had $" + max);
    }
}
