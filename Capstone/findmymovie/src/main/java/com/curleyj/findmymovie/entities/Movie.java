/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jake
 */
public class Movie {
    private int movieID;
    private String imdbID;
    private String title;
    private int userScore;
    private String releaseDate;
    private String rating;
    private int runTime;
    private String director;
    private String plot;
    private String poster;
    private String popularity;
    private int budget;

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.movieID;
        hash = 53 * hash + Objects.hashCode(this.imdbID);
        hash = 53 * hash + Objects.hashCode(this.title);
        hash = 53 * hash + this.userScore;
        hash = 53 * hash + Objects.hashCode(this.releaseDate);
        hash = 53 * hash + Objects.hashCode(this.rating);
        hash = 53 * hash + this.runTime;
        hash = 53 * hash + Objects.hashCode(this.director);
        hash = 53 * hash + Objects.hashCode(this.plot);
        hash = 53 * hash + Objects.hashCode(this.poster);
        hash = 53 * hash + Objects.hashCode(this.popularity);
        hash = 53 * hash + this.budget;
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
        final Movie other = (Movie) obj;
        if (this.movieID != other.movieID) {
            return false;
        }
        if (this.userScore != other.userScore) {
            return false;
        }
        if (this.runTime != other.runTime) {
            return false;
        }
        if (this.budget != other.budget) {
            return false;
        }
        if (!Objects.equals(this.imdbID, other.imdbID)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.plot, other.plot)) {
            return false;
        }
        if (!Objects.equals(this.poster, other.poster)) {
            return false;
        }
        if (!Objects.equals(this.popularity, other.popularity)) {
            return false;
        }
        return true;
    }
    
    
}
