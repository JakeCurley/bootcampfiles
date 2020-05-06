/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.controller;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Genre;
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


/**
 *
 * @author Jake
 */

@CrossOrigin
@RestController
public class Controller {
    
    @Autowired
    ServiceLayer service;
    
    
    @GetMapping("/getMovieList")
    public ResponseEntity<List<MovieList>> getMovieList() {
        List<MovieList> movieList = service.getAllMovieList();
        
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
    public void addMovieList(@RequestBody MovieListActorGenre mlag) {
        
        Movie movie = new Movie();
        movie.setImdbID(mlag.getImdbID());
        movie.setTitle(mlag.getTitle());
        movie.setPlot(mlag.getPlot());
        movie.setUserScore(mlag.getUserScore());
        movie.setDirector(mlag.getDirector());
        movie.setPoster(mlag.getPoster());
        movie.setRating(mlag.getRating());
        movie.setReleaseDate(mlag.getReleaseDate());
        movie.setRunTime(mlag.getRunTime());
        
        service.addMovieToList(movie);
        service.movieListByName(movie, mlag.getListName());
        
        try {
            String[] actors = mlag.getActors().split(",");
            for (int i=0; i<actors.length; i++) {
                Actor actor = new Actor(actors[i]);
                service.addActor(actor);
                service.addActorToMovie(movie, actor.getActorName());
            }

            String[] genres = mlag.getGenres().split(",");
            for (int i=0; i<genres.length; i++) {
                Genre genre = new Genre(genres[i]);
                service.addGenre(genre);
                service.addGenreToMovie(movie, genre.getGenreName());
            }
        } catch(NullPointerException e) {
            System.out.println("Problem with actors/genres");
        }
    }
    
    @GetMapping("/getCompleteLists")
    public ResponseEntity<List<MovieListActorGenre>> getCompleteLists() {
        List<MovieListActorGenre> mlagList = service.getCompleteLists();
        
        return ResponseEntity.ok(mlagList);
    }
}
