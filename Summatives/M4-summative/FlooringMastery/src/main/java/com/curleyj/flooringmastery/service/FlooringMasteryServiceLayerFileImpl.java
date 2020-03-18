/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dao.FlooringMasteryDao;
import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Jake
 */
public class FlooringMasteryServiceLayerFileImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryDao dao;

    public FlooringMasteryServiceLayerFileImpl(FlooringMasteryDao dao) {
        this.dao = dao;
    }

    @Override
    public void loadOrders() throws Exception {
        dao.loadOrders();
    }

    @Override
    public HashMap<Integer, order> displayOrdersService(LocalDate ld) throws Exception {
        return dao.loadOrdersByDate(ld);
    }

    @Override
    public void validateStateGetTaxRate(order newOrder) throws Exception {
        String state = newOrder.getState();
        BigDecimal taxRate = dao.loadTaxes(state);
        newOrder.setTaxRate(taxRate);
    }

    @Override
    public void validateProductGetCosts(order newOrder) throws Exception {
        String type = newOrder.getProductType();
        product newProduct = dao.loadProducts(type);
        newOrder.setCpsq(newProduct.getCpsf());
        newOrder.setLaborCPSQ(newProduct.getLaborCPSF());
    }

    @Override
    public void moneyCalculations(order newOrder) {
        newOrder.setLaborCost((newOrder.getLaborCPSQ().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost((newOrder.getCpsq().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTax((newOrder.getLaborCost().add(newOrder.getMaterialCost())).multiply(newOrder.getTaxRate().movePointLeft(2)).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTotal((newOrder.getLaborCost().add(newOrder.getMaterialCost()).add(newOrder.getTax())).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public void addToMap(order newOrder) throws Exception {
        dao.addToMap(newOrder);
    }

    @Override
    public HashMap<Integer, order> getMap() {
        return dao.getMapDao();
    }

    @Override
    public order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber) {
        return dao.getOrderNumberByDate(newMap, orderNumber);
    }

    @Override
    public void removeOrder(order newOrder) {
        dao.removeOrder(newOrder);
    }

    @Override
    public void saveCurrentWork() throws Exception {
        dao.writeLibrary();
    }

    @Override
    public counter getCounter() throws Exception {
        return dao.loadCounter();
    }

    @Override
    public void saveCounter(counter currentCount) throws Exception {
        dao.writeCounter(currentCount);
    }

    @Override
    public boolean getMode() throws Exception {
        return dao.setMode();
    }
}
