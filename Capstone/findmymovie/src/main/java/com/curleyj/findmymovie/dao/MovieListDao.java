/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface MovieListDao {
    MovieList add(MovieList movieList);
    List<MovieList> getAllMovieList();
    void movieListByName(Movie movie, String name);
    MovieListActorGenre getCompleteList(MovieList movieList);
    void deleteList(MovieList movieList);
}
