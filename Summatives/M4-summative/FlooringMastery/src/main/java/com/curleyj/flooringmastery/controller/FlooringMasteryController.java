/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.controller;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.service.FlooringMasteryServiceLayer;
import com.curleyj.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;



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
        LocalDate ld = view.getDate();
        newMap = service.displayOrdersService(ld);
        if (newMap.isEmpty()) {
            view.errorDisplayOrders();
        }
        else {
            view.displayOrders(newMap);
        }
    }
    
    public void addOrder() throws Exception {   
        counter currentCount = service.getCounter();
        view.bannerAddOrder();
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
            service.saveCounter(currentCount);
        }
    }
    
    public void removeOrder() throws Exception {
        view.bannerRemoveOrder();
        LocalDate ld = view.getDate();
        int orderNumber = view.getOrderNumber();
        HashMap<Integer, order> newMap = service.displayOrdersService(ld);
        order newOrder = service.getOrderNumberByDate(newMap, orderNumber);
        view.displaySingleOrder(newOrder);
        String choice = view.getConfirmationRemove();
        if (choice.equalsIgnoreCase("Y")) {
            service.removeOrder(newOrder);
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
            int orderNumber = view.getOrderNumber();
            order newOrder = service.getOrderNumberByDate(newMap, orderNumber);
            newOrder = view.editOrder(newOrder);
            service.addToMap(newOrder);
        }
    }
    
    public void saveCurrentWork() throws Exception {
        service.saveCurrentWork();
    }
}
