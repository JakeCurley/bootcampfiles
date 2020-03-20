/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dao;


import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Jake
 */
public interface FlooringMasteryDao {
    
    void loadOrders() throws FlooringMasteryPersistenceException;
    HashMap<Integer, order> loadOrdersByDate(LocalDate ld);
    TreeMap<Integer, order> getMapDao();
    BigDecimal loadTaxes(String state) throws FlooringMasteryPersistenceException;
    product loadProducts(String type) throws FlooringMasteryPersistenceException;
    void addToMap(order newOrder) throws FlooringMasteryPersistenceException;
    void writeLibrary() throws FlooringMasteryPersistenceException, Exception;
    order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber) throws FlooringMasteryInvalidOrderException;
    void removeOrder(order newOrder);
    void loadCounter() throws FlooringMasteryPersistenceException;
    void writeCounter(counter counter) throws FlooringMasteryPersistenceException, Exception;
    boolean setMode() throws Exception;
    void addToCounter(counter currentCount);
    counter getCurrentCounter();
}
