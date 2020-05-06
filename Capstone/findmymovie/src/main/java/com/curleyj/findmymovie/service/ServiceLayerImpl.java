/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.service;


import com.curleyj.findmymovie.dao.ActorDao;
import com.curleyj.findmymovie.dao.GenreDao;
import com.curleyj.findmymovie.dao.LoginInfoDao;
import com.curleyj.findmymovie.dao.LoginInfoDaoDB;
import com.curleyj.findmymovie.dao.MovieDao;
import com.curleyj.findmymovie.dao.MovieListDao;
import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.LoginInfo;
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
    LoginInfoDao liDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    GenreDao genreDao;
    
    @Autowired
    ActorDao actorDao;
    
    @Autowired
    MovieListDao movieListDao;
    
    @Override
    public List<MovieList> getAllMovieList(MovieList ml) {
        return movieListDao.getAllMovieList(ml);
    }
    
    @Override
    public MovieList addList(MovieList movieList) {
        return movieListDao.add(movieList);
    }
    
    @Override
    public Movie addMovieToList(Movie movie, String listName) {
       return movieDao.add(movie, listName);
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
    public void addActorToMovie(Movie movie, Actor actor) {
        movieDao.addActorToMovie(movie, actor);
    }
    
    @Override
    public void addGenreToMovie(Movie movie, Genre genre) {
        movieDao.addGenreToMovie(movie, genre);
    }
    
    @Override
    public MovieListActorGenre getCompleteList(MovieList movieList) {
        return movieListDao.getCompleteList(movieList);
    }
    
    @Override
    public MovieListActorGenre getMyMovie(Movie movie) {
        return movieDao.getMyMovie(movie);
    }
    
    @Override
    public void deleteList(MovieList movieList) {
        movieListDao.deleteList(movieList);
    }
    
    @Override
    public void deleteMovie(Movie movie) {
        movieDao.deleteMovie(movie);
    }
    
    @Override
    public void updateUserScore(Movie movie) {
        movieDao.updateUserScore(movie);
    }
    
    @Override
    public List<Genre> getAllGenres(Movie movie) {
        return genreDao.getAllGenres(movie);
    }
    
    @Override
    public List<Actor> getAllActors(Movie movie) {
        return actorDao.getAllActors(movie);
    }
    
    @Override
    public List<Movie> getAllRatings(Movie movie) {
        return movieDao.getAllRatings(movie);
    }
    
    @Override
    public LoginInfo register(LoginInfo li) {
        return liDao.register(li);
    }
    
    @Override
    public LoginInfo login(LoginInfo li) {
        return liDao.verifyLoginInfo(li);
    }
    
    @Override
    public List<Movie> scoreComparisonChart(Movie movie) {
        return movieDao.scoreComparisonChart(movie);
    }
    
    @Override
    public List<Movie> budgetScoreChart(Movie movie) {
        return movieDao.budgetScoreChart(movie);
    }
    
    @Override
    public List<Movie> movieLengthChart(Movie movie) {
        return movieDao.movieLengthChart(movie);
    }
}
