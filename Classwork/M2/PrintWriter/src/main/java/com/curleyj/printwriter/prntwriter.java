/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.printwriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class prntwriter {
    public static void main(String[] args) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));
        out.println("this is a line in my file...");
        out.println("a second line in my fine...");
        out.println("a third like in my file...");
        out.flush();
        out.close();
        
        Scanner sc = new Scanner(new BufferedReader(new FileReader("OutFile")));
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }
    }
   
}
