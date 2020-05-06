/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.controller;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.LoginInfo;
import com.curleyj.findmymovie.entities.Movie;
import com.curleyj.findmymovie.entities.MovieList;
import com.curleyj.findmymovie.entities.MovieListActorGenre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.curleyj.findmymovie.service.ServiceLayer;
import java.util.ArrayList;
import org.springframework.dao.EmptyResultDataAccessException;


/**
 *
 * @author Jake
 */

@CrossOrigin
@RestController
public class Controller {
    
    @Autowired
    ServiceLayer service;
    
    
    @PostMapping("/getMovieList")
    public ResponseEntity<List<MovieList>> getMovieList(@RequestBody MovieList ml) {
        List<MovieList> movieList = service.getAllMovieList(ml);
        
        if (movieList.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(movieList);
    }
    
    @PostMapping("/addList")
    public ResponseEntity<MovieList> addList(@RequestBody MovieList movieList) {
        MovieList newList = service.addList(movieList);
        
        if (newList == null) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(newList);
    }
    
    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieListActorGenre mlag) {
        
        Movie movie = new Movie();
        movie.setImdbID(mlag.getImdbID());
        movie.setTitle(mlag.getTitle());
        movie.setPlot(mlag.getPlot());
        movie.setUserScore(mlag.getUserScore());
        movie.setPoster(mlag.getPoster());
        movie.setRating(mlag.getRating());
        movie.setReleaseDate(mlag.getReleaseDate());
        movie.setRunTime(mlag.getRunTime());
        movie.setPopularity(mlag.getPopularity());
        movie.setBudget(mlag.getBudget());
        movie.setUserName(mlag.getUserName());
        
        Movie newMovie = service.addMovieToList(movie, mlag.getListName());
       
        if (newMovie == null) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
       
        service.movieListByName(movie, mlag.getListName());
        try {
            String[] actors = mlag.getActors().split(",");
            for (int i=0; i<actors.length; i++) {
                Actor actor = new Actor(actors[i]);
                actor.setUserName(mlag.getUserName());
                service.addActor(actor);
                service.addActorToMovie(movie, actor);
            }

            String[] genres = mlag.getGenres().split(",");
            for (int i=0; i<genres.length; i++) {
                Genre genre = new Genre(genres[i]);
                genre.setUserName(mlag.getUserName());
                service.addGenre(genre);
                service.addGenreToMovie(movie, genre);
            }
        } catch(NullPointerException e) {
            System.out.println("Problem with actors/genres");
        }
        return ResponseEntity.ok(newMovie);
    }
    
    @PostMapping("/getCompleteList")
    public ResponseEntity<MovieListActorGenre> getCompleteList(@RequestBody MovieList movieList) {
        MovieListActorGenre mlag = service.getCompleteList(movieList);
        
        return ResponseEntity.ok(mlag);
    }
    
    @PostMapping("/getMyMovie")
    public ResponseEntity<MovieListActorGenre> getMyMovie(@RequestBody Movie movie) {
        MovieListActorGenre mlag = service.getMyMovie(movie);
        
        return ResponseEntity.ok(mlag);
    }
    
    @PostMapping("/deleteList")
    public void deleteList(@RequestBody MovieList movieList) {
        service.deleteList(movieList);
    }
    
    @PostMapping("/deleteMovie")
    public void deleteMovie(@RequestBody Movie movie) {
        service.deleteMovie(movie);
    }
    
    @PostMapping("/updateUserScore")
    public void updateUserScore(@RequestBody Movie movie) {
        service.updateUserScore(movie);
    }
    
    @PostMapping("/getAllGenres")
    public ResponseEntity<List<Genre>> getAllGenres(@RequestBody Movie movie) {
        List<Genre> genres = service.getAllGenres(movie);
        return ResponseEntity.ok(genres);
    }
    
    @PostMapping("/getAllActors")
    public ResponseEntity<List<Actor>> getAllActors(@RequestBody Movie movie) {
        List<Actor> actors = service.getAllActors(movie);
        
        return ResponseEntity.ok(actors);
    }
    
    @PostMapping("/getAllRatings")
    public ResponseEntity<List<Movie>> getAllRatings(@RequestBody Movie movie) {
        List<Movie> movies = service.getAllRatings(movie);
        
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping("/Register")
    public ResponseEntity<LoginInfo> register(@RequestBody LoginInfo li) {
        LoginInfo newLi = service.register(li);
        
        if (newLi == null) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        
        return ResponseEntity.ok(newLi);
    }
    
    @PostMapping("/Login")
    public ResponseEntity<LoginInfo> login(@RequestBody LoginInfo li) {
        LoginInfo newLi = new LoginInfo();
        try {
            newLi = service.login(li);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        
        if(newLi == null) {
            return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(newLi);
    }
    
    @PostMapping("/scoreComparisonChart")
    public ResponseEntity<List<Movie>> scoreComparisonChart(@RequestBody Movie movie) {
        List<Movie> movies = service.scoreComparisonChart(movie);
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping("/budgetScoreChart")
    public ResponseEntity<List<Movie>> budgetScoreChart(@RequestBody Movie movie) {
        List<Movie> movies = service.budgetScoreChart(movie);
        return ResponseEntity.ok(movies);
    }
    
    @PostMapping("/movieLengthChart")
    public ResponseEntity<List<Movie>> movieLengthChart(@RequestBody Movie movie) {
        List<Movie> movies = service.movieLengthChart(movie);
        return ResponseEntity.ok(movies);
    }
    
    
}
