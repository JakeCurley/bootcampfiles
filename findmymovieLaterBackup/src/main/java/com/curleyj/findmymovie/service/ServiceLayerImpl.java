/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.service;


import com.curleyj.findmymovie.dao.ActorDao;
import com.curleyj.findmymovie.dao.GenreDao;
import com.curleyj.findmymovie.dao.MovieDao;
import com.curleyj.findmymovie.dao.MovieListDao;
import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jake
 */
@Service
public class ServiceLayerImpl implements ServiceLayer {
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    GenreDao genreDao;
    
    @Autowired
    ActorDao actorDao;
    
    @Autowired
    MovieListDao movieListDao;
    
    @Override
    public List<MovieList> getAllMovieList() {
        return movieListDao.getAllMovieList();
    }
    
    @Override
    public MovieList addList(MovieList movieList) {
        return movieListDao.add(movieList);
    }
    
    @Override
    public Movie addMovieToList(Movie movie) {
        return movieDao.add(movie);
    }
    
    @Override
    public void addActor(Actor actor) {
        actorDao.add(actor);
    }
    
    @Override
    public void addGenre(Genre genre) {
        genreDao.add(genre);
    }
    
    @Override
    public void movieListByName(Movie movie, String listName) {
        movieListDao.movieListByName(movie, listName);
    }
    
    @Override
    public void addActorToMovie(Movie movie, String actorName) {
        movieDao.addActorToMovie(movie, actorName);
    }
    
    @Override
    public void addGenreToMovie(Movie movie, String genreName) {
        movieDao.addGenreToMovie(movie, genreName);
    }
    
    @Override
    public List<MovieListActorGenre> getCompleteLists() {
        return movieListDao.getCompleteLists();
    }
}
