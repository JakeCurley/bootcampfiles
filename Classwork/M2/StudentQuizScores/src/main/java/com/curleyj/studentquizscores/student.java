/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.studentquizscores;

import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class student {  //add a student - input name
    private String name;
    private double sc;
    ArrayList<Double> quizScores = new ArrayList<>();
    
    public student(String name) {
        this.name = name;
        
    }
    
    public String getStudent() {
        return this.name;
    }
    
    public void setStudent(String name) {
        this.name = name;
    }
    
    public void addScore(Double sc) {
        quizScores.add(sc);
    }
    
    public ArrayList getScore() {
        return quizScores;
    }
    
    public String toString() {
        return name;
    }
}
