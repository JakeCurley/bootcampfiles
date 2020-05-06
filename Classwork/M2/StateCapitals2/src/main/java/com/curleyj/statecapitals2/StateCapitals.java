/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.statecapitals2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class StateCapitals {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        Capital alabama = new Capital("Montgomery", 205000, 156);
        Capital alaska = new Capital("Juneau", 31000, 3255);
        Capital arizona = new Capital("Phoenix", 1445000, 517);
        Capital arkansas = new Capital("Little Rock", 193000, 116);
        
        HashMap<String, Capital> states = new HashMap<>();
        
        states.put("Alabama", alabama);
        states.put("Alaska", alaska);
        states.put("Arizona", arizona);
        states.put("Arkansas", arkansas);
        
        System.out.println("STATE/CAPITAL PAIRS: ");
        System.out.println("====================");
        for (String k : states.keySet()) {
            System.out.print(k + " - ");
            System.out.println(states.get(k));
        } 
        
        System.out.println("Please end the lower limit for capital city population: ");
        int minPop = sc.nextInt();
        
        System.out.println("LISTING CAPITALS WITH POPULATIONS GREATER THAN " + minPop + ":");
         
        for(String k : states.keySet()) {
            Capital pop = states.get(k);
            int popul = pop.getPopulation();
            if (popul > minPop) {
                System.out.println(states.get(k));
            }
        }
    }
}
