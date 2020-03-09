/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.ui;

import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public String printScreenAndGetSelection() {
        io.print("1. Enter money");
        io.print("2. Display items");
        io.print("3. Exit and recieve change");
        
        return io.readString("Please make a selection.");
    }
    
    public BigDecimal displayAddMoney() {
        return io.readBigDecimal("Enter amount of money");
    }
    
    public void displayAddMoneySuccess(BigDecimal money) {
        io.print("You have added $" + money + " to the machine.");
    }
    
    public String displayDisplayItems(ArrayList<Item> items, BigDecimal money) {
        io.print("=== Available Items ===");
        for (Item s : items) {
            if (s.getQuantity() > 0) {
                System.out.println(s.getName() + "    Press:(" + s.getID() + ")");
                System.out.println("Quantity: " + s.getQuantity());
                System.out.println("Price: $" + s.getPrice());
                System.out.println("================");
            }
        }
        io.print("Available money : $" + money);
        String choice = io.readString("Please make a selection.");
        
        return choice;
    }
    
    public void displayItemChoiceSuccess(Item choice) {
        io.print("You chose " + choice.getName() + "!");           
    }
    
    public void getChange(String money) {
        io.print(money);
    }
    public void displayEnterMoney() {
        io.print("Please enter money before proceeding.");       
    }

    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
    }
    
    public void displayMoneyInvalid() {
        io.print("Please enter a valid number.");
    }
}
