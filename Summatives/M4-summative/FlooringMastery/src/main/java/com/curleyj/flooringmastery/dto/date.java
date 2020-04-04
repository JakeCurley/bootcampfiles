/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dto;

import java.time.LocalDate;

/**
 *
 * @author Jake
 */
public class date {
    LocalDate ld;
    public date(LocalDate ld) {
        this.ld = ld;
    }
    
    public LocalDate getLd() {
        return ld;
    }
}
