/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Jake
 */
public class product {
    
    String name;
    BigDecimal Cpsf;
    BigDecimal laborCPSF;
    
    public product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCpsf() {
        return Cpsf;
    }

    public void setCpsf(BigDecimal Cpsf) {
        this.Cpsf = Cpsf;
    }

    public BigDecimal getLaborCPSF() {
        return laborCPSF;
    }

    public void setLaborCPSF(BigDecimal laborCPSF) {
        this.laborCPSF = laborCPSF;
    }
    
    
}
