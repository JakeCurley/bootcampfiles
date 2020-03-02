/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.inheritencelabs;

import java.util.Arrays;

/**
 *
 * @author Jake
 */
public class PersonnelTest {
    private Person person;
    private int i;
    private Person[] personArray = new Person[7];
    
    public PersonnelTest() {
        i = 0;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public Person getPerson() {
        return person;
    }
    
    public Person[] add(Person person) {
            personArray[i] = person;
            person.compareTo(person);
            i++;
            
            return personArray;
    }
    
    public void print(Person[] personArray) {
        super.toString();
        for (i = 0; i < personArray.length; i++) {
            System.out.println(personArray[i]);
        }
    }
    
    
}
