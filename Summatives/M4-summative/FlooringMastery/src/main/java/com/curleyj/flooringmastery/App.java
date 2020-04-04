/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery;

import com.curleyj.flooringmastery.controller.FlooringMasteryController;
import com.curleyj.flooringmastery.dao.FlooringMasteryDao;
import com.curleyj.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.curleyj.flooringmastery.service.FlooringMasteryServiceLayer;
import com.curleyj.flooringmastery.service.FlooringMasteryServiceLayerFileImpl;
import com.curleyj.flooringmastery.ui.FlooringMasteryView;
import com.curleyj.flooringmastery.ui.UserIO;
import com.curleyj.flooringmastery.ui.UserIOFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) throws Exception {
        
        //UserIO myIo = new UserIOFileImpl();
        //FlooringMasteryView myView = new FlooringMasteryView(myIo);
        //FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
        //FlooringMasteryServiceLayer myService = new FlooringMasteryServiceLayerFileImpl(myDao);
        //FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
        //controller.run();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        
        controller.run();
    }
}
