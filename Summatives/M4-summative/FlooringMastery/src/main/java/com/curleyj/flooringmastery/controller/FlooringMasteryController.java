/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.controller;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidInputException;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidOrderException;
import com.curleyj.flooringmastery.service.FlooringMasteryServiceLayer;
import com.curleyj.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.TreeMap;



/**
 *
 * @author Jake
 */
public class FlooringMasteryController {
    
    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;


    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws Exception {
        boolean keepGoing = true;
        service.loadOrders();
        service.getCounter();
        boolean mode = service.getMode();
        do {
            int choice = view.getChoiceAndPrintMenu();
            
            switch (choice) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    if (!mode) {
                        saveCurrentWork();
                        break;
                    }
                    else {
                        view.errorTrainingMode();
                        break;
                    }
                    
                case 6:
                    if (!mode) {
                        saveCurrentWork();
                        counter counter = service.getCurrentCounter();
                        service.saveCounter(counter);
                        view.exitDisplaySaved();
                    }
                    else {
                        view.exitDisplayNoSave();
                    }

                    keepGoing = false;
            }
            
        }while (keepGoing);
    }
    
    public void displayOrders() throws Exception {
        
        HashMap<Integer, order> newMap = new HashMap<>();
        TreeMap<Integer, order> orderedMap = new TreeMap<>();
        LocalDate ld = view.getDate();
        newMap = service.displayOrdersService(ld);
        orderedMap.putAll(newMap);
        if (newMap.isEmpty()) {
            view.errorDisplayOrders();
        }
        else {
            view.displayOrders(orderedMap);
        }
    }
    
    public void addOrder() throws Exception {   
        counter currentCount = service.getCurrentCounter();
        view.bannerAddOrder();
        boolean valid = false;
        while (!valid) {
            try {
               order newOrder = view.addOrder(currentCount.getCount()); 
               service.validateStateGetTaxRate(newOrder);
               service.validateProductGetCosts(newOrder);
               service.moneyCalculations(newOrder);
               LocalDate ld = LocalDate.now();
               String formattedDate = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
               newOrder.setDate(formattedDate);
               view.displaySingleOrder(newOrder);
               String choice = view.getConfirmationAdd();
               if (choice.equalsIgnoreCase("Y")) {
                   service.addToMap(newOrder);
                   currentCount.setCount(currentCount.getCount() + 1);
                   service.addToCounter(currentCount);
               }
               valid = true;
            }
            catch (FlooringMasteryInvalidInputException e) {
                view.errorMessage(e.getMessage());
                valid = false;
            }
        }
    }
    
    public void removeOrder() throws Exception {
        view.bannerRemoveOrder();
        LocalDate ld = view.getDate();
        boolean again = true;
        int orderNumber = view.getOrderNumber();
        while (again) {
            HashMap<Integer, order> newMap = service.displayOrdersService(ld);
            try {
                order newOrder = service.getOrderNumberByDate(newMap, orderNumber);
                view.displaySingleOrder(newOrder);
                boolean valid = false;
                while(!valid) {
                    String choice = view.getConfirmationRemove();
                    if (choice.equalsIgnoreCase("Y")) {
                        service.removeOrder(newOrder);
                        valid = true;
                    }
                    else if (!choice.equalsIgnoreCase("N")) {
                        view.errorConfirmation();
                        valid = false;
                    }
                    else {
                        valid = true;
                    }
                }
                again = false;
            }
            catch (FlooringMasteryInvalidOrderException e) {
                view.errorMessage(e.getMessage());
                orderNumber = view.getOrderNumber();
                again = true;
            }
        }
    }
    
    public void editOrder() throws Exception {
        view.bannerEditOrder();
        LocalDate ld = view.getDate();
        HashMap<Integer, order> newMap = service.displayOrdersService(ld);
        if (newMap.isEmpty()) {
            view.errorDisplayOrders();
        }
        else {
            boolean again = true;
            while(again) {
                try {
                    int orderNumber = view.getOrderNumber();
                    order newOrder = service.getOrderNumberByDate(newMap, orderNumber);
                    newOrder = view.editOrder(newOrder);
                    service.validateStateGetTaxRate(newOrder);
                    service.validateProductGetCosts(newOrder);
                    service.moneyCalculations(newOrder);
                    service.addToMap(newOrder);
                    again = false;
                }
                catch (FlooringMasteryInvalidOrderException e) {
                    view.errorMessage(e.getMessage());
                    again = true;
                }
            }
        }
    }
    
    public void saveCurrentWork() throws Exception {
        service.saveCurrentWork();
        counter counter = service.getCurrentCounter();
        service.saveCounter(counter);
    }
}
