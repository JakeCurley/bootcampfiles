/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dao.FlooringMasteryDao;
import com.curleyj.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Jake
 */
public class FlooringMasteryServiceLayerFileImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryDao dao;
    int counter;

    public FlooringMasteryServiceLayerFileImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadOrders() throws FlooringMasteryPersistenceException {
        dao.loadOrders();
    }

    @Override
    public HashMap<Integer, order> displayOrdersService(LocalDate ld) throws FlooringMasteryPersistenceException {
        return dao.loadOrdersByDate(ld);
    }

    @Override
    public void validateStateGetTaxRate(order newOrder) throws FlooringMasteryPersistenceException {
        String state = newOrder.getState();
        if (state.equalsIgnoreCase("mi")) {
            BigDecimal taxRate = dao.loadTaxes("MI");
            newOrder.setTaxRate(taxRate);
        }
        if (state.equalsIgnoreCase("oh")) {
            BigDecimal taxRate = dao.loadTaxes("OH");
            newOrder.setTaxRate(taxRate);
        }
        if (state.equalsIgnoreCase("pa")) {
            BigDecimal taxRate = dao.loadTaxes("PA");
            newOrder.setTaxRate(taxRate);
        }
        if (state.equalsIgnoreCase("in")) {
            BigDecimal taxRate = dao.loadTaxes("IN");
            newOrder.setTaxRate(taxRate);
        }

    }

    @Override
    public void validateProductGetCosts(order newOrder) throws FlooringMasteryPersistenceException {
        String type = newOrder.getProductType();
        if (type.equalsIgnoreCase("wood")) {
            product newProduct = dao.loadProducts("Wood");
            newOrder.setCpsq(newProduct.getCpsf());
            newOrder.setLaborCPSQ(newProduct.getLaborCPSF());
        }
        else if (type.equalsIgnoreCase("carpet")) {
            product newProduct = dao.loadProducts("Carpet");
            newOrder.setCpsq(newProduct.getCpsf());
            newOrder.setLaborCPSQ(newProduct.getLaborCPSF());
        }
        else if (type.equalsIgnoreCase("tile")) {
            product newProduct = dao.loadProducts("Tile");
            newOrder.setCpsq(newProduct.getCpsf());
            newOrder.setLaborCPSQ(newProduct.getLaborCPSF());
        }
        else if (type.equalsIgnoreCase("laminate")) {
            product newProduct = dao.loadProducts("Laminate");
            newOrder.setCpsq(newProduct.getCpsf());
            newOrder.setLaborCPSQ(newProduct.getLaborCPSF());
        }
    }

    @Override
    public void moneyCalculations(order newOrder) {
        newOrder.setLaborCost((newOrder.getLaborCPSQ().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost((newOrder.getCpsq().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTax((newOrder.getLaborCost().add(newOrder.getMaterialCost())).multiply(newOrder.getTaxRate().movePointLeft(2)).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTotal((newOrder.getLaborCost().add(newOrder.getMaterialCost()).add(newOrder.getTax())).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public void addToMap(order newOrder) throws FlooringMasteryPersistenceException {
        dao.addToMap(newOrder);
    }

    @Override
    public TreeMap<Integer, order> getMap() {
        return dao.getMapDao();
    }

    @Override
    public order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber) throws FlooringMasteryInvalidOrderException {
        return dao.getOrderNumberByDate(newMap, orderNumber);
    }

    @Override
    public void removeOrder(order newOrder) {
        try {
            dao.removeOrder(newOrder);
        }
        catch(NumberFormatException e) {
            System.out.println("test");
        }
    }

    @Override
    public void saveCurrentWork() throws FlooringMasteryPersistenceException, Exception {
        dao.writeLibrary();
    }

    @Override
    public counter getCounter() throws FlooringMasteryPersistenceException {
        return dao.loadCounter();
    }

    @Override
    public void saveCounter(counter currentCount) throws FlooringMasteryPersistenceException, Exception {
        dao.writeCounter(currentCount);
    }

    @Override
    public boolean getMode() throws FlooringMasteryPersistenceException, Exception {
        return dao.setMode();
    }
}
