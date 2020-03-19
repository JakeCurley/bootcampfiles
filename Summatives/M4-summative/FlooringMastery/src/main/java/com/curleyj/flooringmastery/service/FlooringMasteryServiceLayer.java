/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Jake
 */
public interface FlooringMasteryServiceLayer {
   
    void loadOrders() throws FlooringMasteryPersistenceException;
    HashMap<Integer, order> displayOrdersService(LocalDate ld) throws FlooringMasteryPersistenceException;
   void validateStateGetTaxRate(order newOrder) throws FlooringMasteryPersistenceException;
   void validateProductGetCosts(order newOrder) throws FlooringMasteryPersistenceException;
   void moneyCalculations(order newOrder);
   TreeMap<Integer, order> getMap();
   void addToMap(order newOrder) throws FlooringMasteryPersistenceException;
   order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber) throws FlooringMasteryInvalidOrderException;
   void removeOrder(order newOrder);
   void saveCurrentWork() throws FlooringMasteryPersistenceException, Exception;
   counter getCounter() throws FlooringMasteryPersistenceException, Exception;
   void saveCounter(counter currentCount) throws FlooringMasteryPersistenceException, Exception;
   boolean getMode() throws FlooringMasteryPersistenceException, Exception;
}
