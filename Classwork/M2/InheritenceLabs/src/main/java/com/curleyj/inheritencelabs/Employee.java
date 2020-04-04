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
public class Employee extends Person {
    private double salary;
    
    public Employee(String name, int age, Address address, double salary) {
        super(name, age, address);
        this.salary = salary;    
    }
    
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        super.toString();
       return name + " " + age + " " + address + " " + salary;
    }
}
