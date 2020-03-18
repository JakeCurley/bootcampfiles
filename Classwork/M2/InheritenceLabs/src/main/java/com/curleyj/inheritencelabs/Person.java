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
public class Person implements Comparable {
       
        protected String name;
        protected int age;
        protected Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return this.name + " " + this.age + " " + this.address;
        }
        
        public void compareTo(Person person) {
         
        }
}

