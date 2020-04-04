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
public class QuizScores {
    private String name;
    private double scores;
    
    public QuizScores(String name) {
        this.name = name;
        this.scores = scores;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getScores() {
        return this.scores;
    }
    
    public void setScores(double scores) {
        this.scores = scores;
    }
    
    public void addScore(double scores) {
        this.scores = scores;
        ArrayList<Double> quiz = new ArrayList<>();
        quiz.add(scores);
        for (Double s : quiz) {
            System.out.println(s);
        }
    }
    
    
    public String toString() {
        return name + " " + scores;
    }     
}
