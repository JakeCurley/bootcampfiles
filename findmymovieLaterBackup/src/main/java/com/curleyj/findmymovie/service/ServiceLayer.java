/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.service;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
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
    Movie addMovieToList(Movie movie);
    void addActor(Actor actor);
    void addGenre(Genre genre);
    void movieListByName(Movie movie, String listName);
    void addActorToMovie(Movie movie, String actorName);
    void addGenreToMovie(Movie movie, String genreName);
    List<MovieListActorGenre> getCompleteLists();
}
