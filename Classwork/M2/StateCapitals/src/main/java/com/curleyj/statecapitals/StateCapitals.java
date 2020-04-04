/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.statecapitals;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Jake
 */
public class StateCapitals {
    public static void main(String[] args) {
        HashMap<String, String> capitals = new HashMap<>();
        
        capitals.put("Alabama","Montgomery");
        capitals.put("Alaska","Juneau");
        capitals.put("Arizona","Phoenix");
        capitals.put("Arkansas","Little Rock");
        
        Set<String> keys = capitals.keySet();
        
        System.out.println("STATES: ");
        System.out.println("=======");
        for (String k : keys) {
            System.out.println(k);
        }
        
        System.out.println("\nCAPITALS: ");
        System.out.println("=========");
        for(String k : keys) {
            System.out.println(capitals.get(k));
        }
        System.out.println("\nSTATE/CAPITAL PAIRS: ");
        System.out.println("==================");
        for (String k : keys) {
            System.out.println(k + " " + "-" + capitals.get(k));
        }
    }
}
