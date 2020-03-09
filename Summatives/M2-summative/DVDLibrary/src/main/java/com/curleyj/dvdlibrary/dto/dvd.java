/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 *
 * @author Jake
 */
public class dvd {
    private String title;
    private LocalDate ld;
    private String rating;
    private String directorName;
    private String studio;
    private String userRating;
    
    public dvd(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getld() {
        return ld;
    }

    public void setld(LocalDate ld) {
        this.ld = ld;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
    
    public long getDvdAge() {
        Period p = ld.until(LocalDate.now());
        return p.getYears();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.ld);
        hash = 83 * hash + Objects.hashCode(this.rating);
        hash = 83 * hash + Objects.hashCode(this.directorName);
        hash = 83 * hash + Objects.hashCode(this.studio);
        hash = 83 * hash + Objects.hashCode(this.userRating);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final dvd other = (dvd) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.ld, other.ld)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        return true;
    }
    
    
    
}
