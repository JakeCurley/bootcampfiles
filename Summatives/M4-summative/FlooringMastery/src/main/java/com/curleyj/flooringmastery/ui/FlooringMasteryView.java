/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.ui;

import com.curleyj.flooringmastery.dto.order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Jake
 */
public class FlooringMasteryView {
    
    UserIO io;
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    
    public int getChoiceAndPrintMenu() {
        io.print("****************************************");
        io.print("* <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");
        io.print("****************************************");
        
        return io.readInt("Please make a selection");
    }
    
    public LocalDate getDate() {
        return io.readLocalDate("Please enter a year.");
    }
    
    public void displayOrders(HashMap<Integer, order> newMap) {
        
        Set<Integer> keys = newMap.keySet();
       
    // print the keys and associated values to the screen
        for (Integer k : keys) {
            io.print("======================================");
            io.print("Order Number: " + k.toString());
            io.print("Customer Name: " + newMap.get(k).getCustomerName());
            io.print("State: " + newMap.get(k).getState());
            io.print("Tax Rate: " + newMap.get(k).getTaxRate());
            io.print("Product Type: " + newMap.get(k).getProductType());
            io.print("Area: " + newMap.get(k).getArea());
            io.print("Cost Per Square Foot: " + newMap.get(k).getCpsq());
            io.print("Labor Cost Per Square Foot: " + newMap.get(k).getLaborCPSQ());
            io.print("Material Cost: " + newMap.get(k).getMaterialCost());
            io.print("Labor Cost: " + newMap.get(k).getLaborCost());
            io.print("Tax: " + newMap.get(k).getTax());
            io.print("Total: " + newMap.get(k).getTotal());
            io.print("Order date: " + newMap.get(k).getDate());
            io.print("======================================");
        }
    }
    
    public void displaySingleOrder(order newOrder) {
        io.print("======================================");
            io.print("Order Number: " + newOrder.getOrderNumber());
            io.print("Customer Name: " + newOrder.getCustomerName());
            io.print("State: " + newOrder.getState());
            io.print("Tax Rate: " + newOrder.getTaxRate());
            io.print("Product Type: " + newOrder.getProductType());
            io.print("Area: " + newOrder.getArea());
            io.print("Cost Per Square Foot: " + newOrder.getCpsq());
            io.print("Labor Cost Per Square Foot: " + newOrder.getLaborCPSQ());
            io.print("Material Cost: " + newOrder.getMaterialCost());
            io.print("Labor Cost: " + newOrder.getLaborCost());
            io.print("Tax: " + newOrder.getTax());
            io.print("Total: " + newOrder.getTotal());
            io.print("Order date: " + newOrder.getDate());
            io.print("======================================");
    }
    
    public void bannerAddOrder() {
        io.print("==== Add an Order ====");
    }
    
    public order addOrder(int size) {
        order newOrder = new order(size);
        newOrder.setCustomerName(io.readString("Please enter customer name: "));
        newOrder.setState(io.readString("Plase enter state name: "));
        newOrder.setProductType(io.readString("Please enter the product type: "));
        newOrder.setArea(io.readBigDecimal("Please enter the area: "));
        
        return newOrder;
    }
    
    public void bannerRemoveOrder() {
        io.print("==== Remove an Order ====");
    }    
    
    public int getOrderNumber() {
       return io.readInt("Enter the order number: ");
    }
    
    public String getConfirmationRemove() {
        return io.readString("Are you sure you would like to remove this order? (Y/N): ");
    }
    public String getConfirmationAdd() {
        return io.readString("Are you sure you would like to add this order? (Y/N)");
    }
    
    public void bannerEditOrder() {
        io.print("==== Edit Order ====");
    }
    
    public order editOrder(order order) {
        String newName = io.readString("Please enter customer name (" + order.getCustomerName() + "): ");
        
        if (!newName.equals("")){
            order.setCustomerName(newName);
        }
        String newState = io.readString("Plase enter state name (" + order.getState() + "): ");
        if (!newState.equals("")) {
            order.setState(newState);
        }
        String newProductType = io.readString("Please enter the product type (" + order.getProductType() + "): ");
        if (!newProductType.equals("")) {
            order.setProductType(newProductType);
        }
        String newArea = io.readString("Please enter the area (" + order.getArea() + "): ");
        if (!newArea.equals("")) {
            order.setArea(new BigDecimal(newArea));
        }
        return order;
    }
    
    public void errorDisplayOrders() {
        io.print("I;m sorry.  There are no orders for that date.");
    }
    
    public void errorTrainingMode() {
        io.print("I'm sorry, the program is in training mode so data cannot be saved.  Please change the configuation file to enable data saving.");
    }
    
    public void exitDisplaySaved() {
        io.print("Program exiting.  Your data will be saved.");
    }
    
    public void exitDisplayNoSave() {
        io.print("Program exiting.  Program is in training mode so data will not be saved.");
    }
}
