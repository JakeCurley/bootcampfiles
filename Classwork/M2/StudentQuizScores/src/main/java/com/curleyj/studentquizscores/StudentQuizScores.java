/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.studentquizscores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Jake
 */
public class StudentQuizScores {
    public static void main(String[] args) {
        User us = new User();
        boolean anotherScore = true;
        boolean addStudent = false;
        boolean viewStudent = false;
        boolean removeStudent = false;
        boolean listScores = false;
        boolean averageScore = false;
        boolean run = true;
        double max  = 0;
        double average = 0;
        double newCurrent = 0;
        double counter = 0;
        HashMap<String, ArrayList> studentScores = new HashMap<>();
        
        while (run) {
            us.print("1. View a list of students in the system\n");
            us.print("2. Add a student to the system\n");
            us.print("3. Remove a student from the system\n");
            us.print("4. View a list of quiz scores for each student\n");
            us.print("5. View the average quiz score for a given student\n");
            us.print("6. Exit program.");
            String answer = us.readString("What would you like to do? ");

            switch (answer) {
                case "1":
                    viewStudent = true;
                    break;
                case "2":
                    addStudent = true;
                    break;
                case "3":
                    removeStudent = true;
                    break;
                case "4":
                    listScores = true;
                    break;
                case "5":
                    averageScore = true;
                    break;
                case "6":
                    run = false;
            }

                while (viewStudent) {
                    Set<String> keys = studentScores.keySet();
                    for (String k : keys) {
                        System.out.println(k);
                    }
                    viewStudent = false;
                }
                while (addStudent) {

                    String input = us.readString("Enter a name");
                    student x = new student(input);
                    
                    while (anotherScore) {
                        double sc = us.readDouble("Add a score");
                        x.addScore(sc);
                        studentScores.put(input,x.getScore());

                        answer = us.readString("Add another score? (y/n)");

                        if (answer.equals("y")) {
                            anotherScore = true;
                        }

                        else {
                            anotherScore = false;
                        }
                    }
                    anotherScore = true;
                    Set<String> keys = studentScores.keySet();
                    for (String k : keys) {
                        System.out.println(k + " " + studentScores.get(k));
                    }

                    answer = us.readString("Would you like to add another student? (y/n) ");

                    if (!answer.equals("y")) {
                        addStudent = false;
                    }
                }
                
                while (removeStudent) {
                        answer = us.readString("Enter a name to remove: ");
                        studentScores.remove(answer);
                        removeStudent = false;
                    }
                
                while(listScores) {
                    
                   String input = us.readString("Enter a name to view scores.");
                   ArrayList scr = studentScores.get(input);
                    
                    Iterator<Double> iter = scr.iterator();
     
                    while(iter.hasNext()) {
                        double current = iter.next();
                        System.out.println(current);
                    }
                    
                    listScores = false;
                }
                
                while (averageScore) {
                   String input = us.readString("Enter a name to view average scores.");
                   ArrayList scr = studentScores.get(input);
                    
                    Iterator<Double> iter = scr.iterator();
     
                    while(iter.hasNext()) {
                        double current = iter.next();
                        newCurrent = current + newCurrent;
                        counter++;
                    }
                
                    average = newCurrent/counter;
                    System.out.println(average);
                    averageScore = false;
                    newCurrent = 0;
                    counter = 0;
                }
            for (int i = 0; i < studentScores.size();i++) {
                    Set<String> keys = studentScores.keySet();
                    
                    for (String k : keys) {
                        ArrayList scr = studentScores.get(k);
                        Iterator<Double> iter = scr.iterator();
       
                        while(iter.hasNext()) {
                            double current = iter.next();
                            newCurrent = current + newCurrent;
                            counter++;
                            if (current > max) {
                                max = current;
                                Set<String> winner = keys;
                                System.out.println(winner);
                            }
                        }
                    }
                    
                    
           }
                average = newCurrent/counter;
                System.out.println("Average score across all students: " +average);
        }
        
        
    }
}
