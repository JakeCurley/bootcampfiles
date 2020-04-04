/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.guessinggame.controller;

import com.curleyj.guessinggame.dto.game;
import com.curleyj.guessinggame.dto.gameGuess;
import com.curleyj.guessinggame.dto.guess;
import com.curleyj.guessinggame.service.ServiceLayer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jake
 */
@RestController
@RequestMapping("/guessGame")
public class Controller {
    private final ServiceLayer service;
    
    public Controller(ServiceLayer service) {
        this.service = service;
    }
    
    @GetMapping("/game")
    public List<game> all() {
        return service.getAll();
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public game begin() {
        return service.add();
    }
    
    @PostMapping("/guess")
    public guess guess(@RequestBody gameGuess gameGuess) {
        return service.validateAnswer(gameGuess.getGameID(), gameGuess.getGuess());
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<game> gameById (@PathVariable int id) {
        game game = service.gameById(id);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }
    
    @GetMapping("/rounds/{id}")
    public ResponseEntity<List<guess>> guessById(@PathVariable int id) {
        List<guess> guessList = service.getAllGuessesById(id);
        if (guessList.isEmpty()) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(guessList);
    }
}
