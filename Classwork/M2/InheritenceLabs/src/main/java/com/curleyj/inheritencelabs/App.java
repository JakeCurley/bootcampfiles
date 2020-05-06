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
public class App {
    public static void main(String[] args) {
        Address address = new Address("Fake St", "Minneapolis", "55401");
        Person person = new Person("Jake", 27, address);
        Person person2 = new Person("Jake", 31, address);
        Employee employee = new Employee("Jake", 27, address, 500.00);
        Employee employee2 = new Employee("Jake", 31, address, 1000.000);
        Contractor contractor = new Contractor("Jake", 27, address, true, 20.00);
        Contractor contractor2 = new Contractor("Jake", 31, address, false, 25.00);
        Contractor contractor3 = new Contractor("NotJake", 29, address, false, 19.00);
                
        
        PersonnelTest personnel1 = new PersonnelTest();
        
        //Person[] personArray = {person, person2, employee, employee2, contractor, contractor2};
        //Person[] personArray = new Person[6];
        
        Person[] personArray = new Person[7];
        
        personArray = 
        personArray = personnel1.add(person);
        personArray = personnel1.add(person2);
        personArray = personnel1.add(employee);
        personArray = personnel1.add(employee2);
        personArray = personnel1.add(contractor);
        personArray = personnel1.add(contractor2);
        personArray = personnel1.add(contractor3);
        
        personnel1.print(personArray);
        //personnel1.print(personArray);
       //System.out.println(personnel1.toString());
    }           
}
