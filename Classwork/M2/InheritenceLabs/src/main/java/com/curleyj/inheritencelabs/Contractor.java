/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.inheritencelabs;

/**
 *
 * @author Jake
 */
public class Contractor extends Person {
    private boolean permanent;
    private double hourlyRate;
    
    public Contractor(String name, int age, Address address, boolean permanent, double hourlyRate) {
        super(name, age, address);
        this.permanent = permanent;
        this.hourlyRate = hourlyRate;
    }
    
    public void setHourlyRate (double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    public String toString() {
        super.toString();
        return name + " " + age + " " + address + " " + permanent + " " + hourlyRate;
    }
}
