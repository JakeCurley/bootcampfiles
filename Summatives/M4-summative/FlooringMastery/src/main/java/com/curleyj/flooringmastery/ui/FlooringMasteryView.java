/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.ui;

import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidInputException;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidOrderException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeMap;

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

        return io.readInt("Please make a selection", 0, 7);
    }

    public LocalDate getDate() {
        return io.readLocalDate("Please enter a year.");
    }

    public void displayOrders(TreeMap<Integer, order> newMap) {

        Set<Integer> keys = newMap.keySet();

        // print the keys and associated values to the screen
        for (Integer k : keys) {
            io.print("======================================");
            io.print("Order Number: " + k.toString());
            io.print("Customer Name: " + newMap.get(k).getCustomerName());
            io.print("State: " + newMap.get(k).getState().toUpperCase());
            io.print("Tax Rate: " + newMap.get(k).getTaxRate() + "%");
            String productType = newMap.get(k).getProductType().substring(0, 1).toUpperCase() + newMap.get(k).getProductType().substring(1).toLowerCase();
            io.print("Product Type: " + productType);
            io.print("Area: " + newMap.get(k).getArea());
            io.print("Cost Per Square Foot: $" + newMap.get(k).getCpsq());
            io.print("Labor Cost Per Square Foot: $" + newMap.get(k).getLaborCPSQ());
            io.print("Material Cost: $" + newMap.get(k).getMaterialCost());
            io.print("Labor Cost: $" + newMap.get(k).getLaborCost());
            io.print("Tax: $" + newMap.get(k).getTax());
            io.print("Total: $" + newMap.get(k).getTotal());
            io.print("Date Added: " + newMap.get(k).getDate().substring(0, 2) + "/" + newMap.get(k).getDate().substring(2, 4) + "/" + newMap.get(k).getDate().substring(4, 8));
            io.print("======================================");
        }
    }

    public void displaySingleOrder(order newOrder) {
        io.print("======================================");
        io.print("Order Number: " + newOrder.getOrderNumber());
        io.print("Customer Name: " + newOrder.getCustomerName());
        io.print("State: " + newOrder.getState().toUpperCase());
        io.print("Tax Rate: " + newOrder.getTaxRate() + "%");
        String productType = newOrder.getProductType().substring(0, 1).toUpperCase() + newOrder.getProductType().substring(1).toLowerCase();
        io.print("Product Type: " + productType);
        io.print("Area: " + newOrder.getArea());
        io.print("Cost Per Square Foot: $" + newOrder.getCpsq());
        io.print("Labor Cost Per Square Foot: $" + newOrder.getLaborCPSQ());
        io.print("Material Cost: $" + newOrder.getMaterialCost());
        io.print("Labor Cost: $" + newOrder.getLaborCost());
        io.print("Tax: $" + newOrder.getTax());
        io.print("Total: $" + newOrder.getTotal());
        io.print("Date Added: " + newOrder.getDate().substring(0, 2) + "/" + newOrder.getDate().substring(2, 4) + "/" + newOrder.getDate().substring(4, 8));
        io.print("======================================");
    }

    public void bannerAddOrder() {
        io.print("==== Add an Order ====");
    }

    public order addOrder(int size) throws FlooringMasteryInvalidInputException {
        order newOrder = new order(size);
        boolean valid = false;
        while (!valid) {
            newOrder.setCustomerName(io.readString("Please enter customer name: "));
            if (!newOrder.getCustomerName().equals("") && newOrder.getCustomerName().substring(0, 1).matches("[a-zA-Z]")) {
                valid = true;
            }
        }
        while (valid) {
            newOrder.setState(io.readString("Plase enter state name (MI/OH/PA/IN): "));
            if (newOrder.getState().equalsIgnoreCase("OH") || newOrder.getState().equalsIgnoreCase("MI") || newOrder.getState().equalsIgnoreCase("IN") || newOrder.getState().equalsIgnoreCase("PA")) {
                valid = false;
            }
        }
        while (!valid) {
            newOrder.setProductType(io.readString("Please enter the product type (Wood/Laminate/Tile/Carpet): "));
            if (newOrder.getProductType().equalsIgnoreCase("Carpet") || newOrder.getProductType().equalsIgnoreCase("Laminate") || newOrder.getProductType().equalsIgnoreCase("Tile") || newOrder.getProductType().equalsIgnoreCase("Wood")) {
                valid = true;
            }
        }
        while (valid) {
            BigDecimal area = io.readBigDecimal("Please enter the area: ", new BigDecimal("0"));
            area.setScale(2, RoundingMode.HALF_UP);
            try {
                newOrder.setArea(area);
                valid = false;
            } catch (NumberFormatException e) {
                io.print("Please enter a number: ");
                valid = true;
            }
        }
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

    public order editOrder(order order) throws FlooringMasteryInvalidOrderException {
        try {
            boolean valid = true;
            while(valid) {
                String newName = io.readString("Please enter customer name (" + order.getCustomerName() + "): ");
                if (!newName.equals("") && newName.substring(0, 1).matches("[a-zA-Z]")) {
                    order.setCustomerName(newName);
                    valid = false;
                }
                else if (newName.equals("")) {
                    valid = false;
                }
            }
            while(!valid) {
                String newState = io.readString("Plase enter state name (" + order.getState().toUpperCase() + "): ");
                if (newState.equalsIgnoreCase("OH") || newState.equalsIgnoreCase("MI") || newState.equalsIgnoreCase("IN") || newState.equalsIgnoreCase("PA")) {
                    order.setState(newState);
                    valid = true;
                }
                else if(newState.equalsIgnoreCase("")){
                    valid = true;
                }
            }
            while(valid) {
                String newProductType = io.readString("Please enter the product type (" + order.getProductType().substring(0, 1).toUpperCase() + order.getProductType().substring(1).toLowerCase() + "): ");
                if (newProductType.equalsIgnoreCase("Carpet") || newProductType.equalsIgnoreCase("Laminate") || newProductType.equalsIgnoreCase("Tile") || newProductType.equalsIgnoreCase("Wood")) {
                    order.setProductType(newProductType);
                    valid = false;
                }
                else if (newProductType.equalsIgnoreCase("")) {
                    valid = false;
                }
            }
            while (!valid) {
            String area = io.readString("Please enter the area: ");
            if (area.equals("")) {
                    return order;
                }
            try {
                BigDecimal bArea = new BigDecimal(area).setScale(2, RoundingMode.HALF_UP);
                order.setArea(bArea);
                break;

            } catch (NumberFormatException e) {
                io.print("Please enter a number: ");
            }
            }
        } catch (NullPointerException e) {
            throw new FlooringMasteryInvalidOrderException("Please enter a valid order number.", e);
        }
        return order;
    }

    public void errorDisplayOrders() {
        io.print("I'm sorry.  There are no orders for that date.");
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

    public void errorInvalidOrderNumber() {
        io.print("That order number does not exsist.  Please reenter a valid order number.");
    }

    public void errorMessage(String prompt) {
        io.print(prompt);
    }

    public void errorConfirmation() {
        io.print("Please choose (Y/N): ");
    }
    
    public void saveSuccess() {
        io.print("Orders saved.");
    }
}
