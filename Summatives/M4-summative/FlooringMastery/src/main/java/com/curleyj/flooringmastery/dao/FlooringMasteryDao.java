/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dao;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.dto.taxes;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Jake
 */
public interface FlooringMasteryDao {
    
    void loadOrders() throws Exception;
    HashMap<Integer, order> loadOrdersByDate(LocalDate ld);
    HashMap<Integer, order> getMapDao();
    BigDecimal loadTaxes(String state) throws Exception;
    product loadProducts(String type) throws Exception;
    void addToMap(order newOrder) throws Exception;
    void writeLibrary() throws Exception;
    order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber);
    void removeOrder(order newOrder);
    counter loadCounter() throws Exception;
    void writeCounter(counter counter) throws Exception;
    boolean setMode() throws Exception ;
}
