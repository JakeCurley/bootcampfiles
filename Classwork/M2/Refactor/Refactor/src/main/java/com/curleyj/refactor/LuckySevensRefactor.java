/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.refactor;

import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class LuckySevensRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many dollars do you have to bet? ");
        int bet = sc.nextInt();
        
        Rolls myRoll = new Rolls();
        
        myRoll.roll(bet);
    }
}
