/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Genre;
import com.curleyj.findmymovie.entities.Movie;
import java.util.List;

/**
 *
 * @author Jake
 */

public interface GenreDao {
    void add(Genre genre);
    List<Genre> getAllGenres(Movie movie);
}
