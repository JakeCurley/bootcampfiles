/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.curleyj.findmymovie.service.ServiceLayer;
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
}
