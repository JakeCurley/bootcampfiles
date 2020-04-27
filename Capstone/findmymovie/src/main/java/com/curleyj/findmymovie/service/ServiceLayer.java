/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.service;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.LoginInfo;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;

/**
 *
 * @author Jake
 */

public interface ServiceLayer {
    
    List<MovieList> getAllMovieList();
    MovieList addList(MovieList movieList);
    Movie addMovieToList(Movie movie, String listName);
    void addActor(Actor actor);
    void addGenre(Genre genre);
    void movieListByName(Movie movie, String listName);
    void addActorToMovie(Movie movie, Actor actor);
    void addGenreToMovie(Movie movie, Genre genre);
    MovieListActorGenre getCompleteList(MovieList movieList);
    MovieListActorGenre getMyMovie(Movie movie);
    void deleteList(MovieList movieList);
    void deleteMovie(Movie movie);
    void updateUserScore(Movie movie);
    List<Genre> getAllGenres();
    List<Actor> getAllActors();
    LoginInfo register(LoginInfo li);
    LoginInfo login(LoginInfo li);
    List<Movie> scoreComparisonChart();
    List<Movie> budgetScoreChart();
    List<Movie> movieLengthChart();
}
