/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.findmymovie.dao;

import com.curleyj.findmymovie.entities.Actor;
import com.curleyj.findmymovie.entities.Movie;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface ActorDao {
    void add(Actor actorName);
    List<Actor> getAllActors(Movie movie);
}
