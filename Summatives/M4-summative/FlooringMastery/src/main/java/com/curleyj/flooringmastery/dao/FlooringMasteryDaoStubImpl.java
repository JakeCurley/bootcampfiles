/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dao;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.date;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Jake
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {
    
    order newOrder;
    order newOrder2;
    TreeMap<Integer, order> newMap = new TreeMap<>();
    
    public FlooringMasteryDaoStubImpl() {
        newOrder = new order(1);
        newOrder.setCustomerName("Curley");
        newOrder.setState("IN");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("30.00"));
        newOrder.setCpsq(new BigDecimal("5.15"));
        newOrder.setLaborCPSQ(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("154.50"));
        newOrder.setLaborCost(new BigDecimal("142.50"));
        newOrder.setTax(new BigDecimal("17.82"));
        newOrder.setTotal(new BigDecimal("314.82"));
        newOrder.setDate("03172020");
        newMap.put(newOrder.getOrderNumber(), newOrder);
        
        newOrder2 = new order(2);
        newOrder2.setCustomerName("Curley2");
        newOrder2.setState("OH");
        newOrder2.setTaxRate(new BigDecimal("6.25"));
        newOrder2.setProductType("Carpet");
        newOrder2.setCpsq(new BigDecimal("2.25"));
        newOrder2.setLaborCPSQ(new BigDecimal("2.10"));
        newOrder2.setMaterialCost(new BigDecimal("67.50"));
        newOrder2.setLaborCost(new BigDecimal("63.00"));
        newOrder2.setTax(new BigDecimal("8.16"));
        newOrder2.setTotal(new BigDecimal("138.66"));
        newOrder2.setArea(new BigDecimal("30.00"));
        newOrder2.setDate("03182020");
        newMap.put(newOrder2.getOrderNumber(), newOrder2);
        
    }

    @Override
    public void loadOrders() throws FlooringMasteryPersistenceException {
        //info hardcoded into map
    }

    @Override
    public HashMap<Integer, order> loadOrdersByDate(LocalDate ld) {
        String date = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        HashMap<Integer, order> testMap = new HashMap<>();
        //testMap.putAll(newMap);
        Set<Integer> orderKey = newMap.keySet();
        for (Integer k : orderKey) {
            if (newMap.get(k).getDate().equals(date)) {
                String test = newMap.get(k).getDate();
                if (test.equals(date)) {
                    testMap.put(k, newMap.get(k));
                }
            }
        }
        
        return testMap;
    }

    @Override
    public TreeMap<Integer, order> getMapDao() {
        return newMap;
    }

    @Override
    public BigDecimal loadTaxes(String state) throws FlooringMasteryPersistenceException {
        BigDecimal taxes = new BigDecimal("0");
        if (state.equalsIgnoreCase("IN")) {
            taxes = new BigDecimal("6.00");
        }
        if (state.equalsIgnoreCase("OH")) {
            taxes = new BigDecimal("6.25");
        }
        
        return taxes;
    }

    @Override
    public product loadProducts(String type) throws FlooringMasteryPersistenceException {
            product product = new product(type);
            if (type.equalsIgnoreCase("wood")) {
                product.setLaborCPSF(new BigDecimal("4.75"));
                product.setCpsf(new BigDecimal("5.15"));
            }
            
            if (type.equalsIgnoreCase("Carpet")) {
                product.setLaborCPSF(new BigDecimal("2.10"));
                product.setCpsf(new BigDecimal("2.25"));
            }
            
            return product;
    }

    @Override
    public void addToMap(order newOrder) {
        newMap.put(newOrder.getOrderNumber(), newOrder);
    }

    @Override
    public void writeOrders() throws FlooringMasteryPersistenceException {
        //not needed to test service layer - tested in Dao test
    }

    @Override
    public order validateOrderNumber(HashMap<Integer, order> newMap, int orderNumber) throws FlooringMasteryInvalidOrderException {
        if (newMap.get(orderNumber) == null) {
            throw new FlooringMasteryInvalidOrderException("That order number does not exsist.");
        }
        return newMap.get(orderNumber);
    }

    @Override
    public void removeOrder(order newOrder) {
        newMap.remove(newOrder.getOrderNumber());
    }

    @Override
    public void loadCounter() throws FlooringMasteryPersistenceException {
        counter counter = new counter("counter");
        counter.setCount(3);
    }

    @Override
    public void writeCounter(counter counter) throws FlooringMasteryPersistenceException {
        //not needed in service layer test - tested in Dao tests
    }

    @Override
    public boolean setMode() throws FlooringMasteryPersistenceException {
        return false;
    }

    @Override
    public void addToCounter(counter currentCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public counter getCurrentCounter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeDate() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDate(date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadDate() throws FlooringMasteryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
