/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.date;
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
   void addToMap(order newOrder);
   order validateOrderNumber(HashMap<Integer, order> newMap, int orderNumber) throws FlooringMasteryInvalidOrderException;
   void removeOrder(order newOrder);
   void writeOrders() throws FlooringMasteryPersistenceException;
   void writeCounter(counter currentCount) throws FlooringMasteryPersistenceException;
   void loadCounter() throws FlooringMasteryPersistenceException;
   boolean getMode() throws FlooringMasteryPersistenceException;
   void addToCounter(counter currentCount);
   counter getCurrentCounter();
   void addDate(date date) throws FlooringMasteryPersistenceException;
   void loadDate() throws FlooringMasteryPersistenceException;
   void writeDate() throws FlooringMasteryPersistenceException;
}
