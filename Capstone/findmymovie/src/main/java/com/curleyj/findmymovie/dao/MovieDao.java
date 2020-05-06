/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;

/**
 *
 * @author Jake
 */

public interface MovieDao {
    Movie add(Movie movie, String listName);
    void addActorToMovie(Movie movie, Actor actor);
    void addGenreToMovie(Movie movie, Genre genre);
    MovieListActorGenre getMyMovie(Movie movie);
    void deleteMovie(Movie movie);
    void updateUserScore(Movie movie);
    List<Movie> scoreComparisonChart(Movie movie);
    List<Movie> budgetScoreChart(Movie movie);
    List<Movie> movieLengthChart(Movie movie);
    List<Movie> getAllRatings(Movie movie);
}
