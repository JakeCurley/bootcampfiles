/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Jake
 */
public interface FlooringMasteryServiceLayer {
   
    void loadOrders() throws Exception;
    HashMap<Integer, order> displayOrdersService(LocalDate ld) throws Exception;
   void validateStateGetTaxRate(order newOrder) throws Exception;
   void validateProductGetCosts(order newOrder) throws Exception;
   void moneyCalculations(order newOrder);
   HashMap<Integer, order> getMap();
   void addToMap(order newOrder) throws Exception;
   order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber);
   void removeOrder(order newOrder);
   void saveCurrentWork() throws Exception;
   counter getCounter() throws Exception;
   void saveCounter(counter currentCount) throws Exception;
   boolean getMode() throws Exception;
   
}
