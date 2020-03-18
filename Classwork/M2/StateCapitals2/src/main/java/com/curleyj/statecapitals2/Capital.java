/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.statecapitals2;

/**
 *
 * @author Jake
 */
public class Capital {
    
    private String name;
    private int population;
    private int squareMilage;
    
    public Capital(String name, int population, int squareMilage) {
        this.name = name;
        this.population = population;
        this.squareMilage = squareMilage;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPopulation() {
        return this.population;
    }
    
    public void setPopulation(int population) {
        this.population = population;
    }
    
    public int getSquareMilage() {
        return this.squareMilage;
    }
    
    public void setSquareMilage(int squareMilage) {
        this.squareMilage = squareMilage;
    }
    
      @Override
      public String toString() {
          return name + " | " + "Pop: " + population + " | " + "Area: " + squareMilage + " sq mi";
      }
}    
