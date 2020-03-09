/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.controller;


import com.curleyj.vendingmachine.dao.VendingMachineDaoException;
import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import com.curleyj.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.curleyj.vendingmachine.service.VendingMachineInvalidSelectionException;
import com.curleyj.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.curleyj.vendingmachine.service.VendingMachineServiceLayer;
import com.curleyj.vendingmachine.ui.UserIO;
import com.curleyj.vendingmachine.ui.UserIOFileImpl;
import com.curleyj.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;
    UserIO io = new UserIOFileImpl();

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineDaoException, VendingMachineInvalidSelectionException {
        boolean keepGoing = true;
        String menuSelection = "";

        while (keepGoing) {
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case "1":
                    addMoney();
                    break;
                case "2":
                    displayItemsAndMakeSelection();
                    break;
                case "3":
                    recieveChange();
                    keepGoing = false;
            }
        }
    }

    public String getMenuSelection() {
        return view.printScreenAndGetSelection();
    }

    public void addMoney() throws VendingMachineDaoException {
        boolean hasErrors = false;
        do {
            try {
                BigDecimal mon = view.displayAddMoney();
                service.addMoney(mon);
                view.displayAddMoneySuccess(mon);
                break;
            }
            catch (NumberFormatException e) {
                view.displayMoneyInvalid();
                hasErrors = true;
            }
        } while (hasErrors = true);
    }

    

    public void displayItemsAndMakeSelection() throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineDaoException, VendingMachineInvalidSelectionException {
        Money money = service.getMoney();

        ArrayList<Item> item = service.displayItems();

        try {
            BigDecimal currentMoney = money.getAmount();
            String choice = view.displayDisplayItems(item, currentMoney);
            Item choiceItem = service.itemSelection(choice);

            if (service.inventoryCheck(choiceItem)) {
                Money oldMoney = new Money("oldMoney");
                oldMoney.setAmount(money.getAmount());
                money = service.updateMoney(choiceItem, money);

                view.displayItemChoiceSuccess(choiceItem);
                String change = service.giveChange(money);
                view.getChange(change);
                service.updateItems(choiceItem);
                money.setAmount(new BigDecimal("0"));
            }
        } 
        catch (VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException | VendingMachineInvalidSelectionException e) {
             view.displayErrorMessage(e.getMessage());
        }
        
        catch (NullPointerException e) {
            view.displayEnterMoney();
        }
    }

    

    public void recieveChange() throws VendingMachineDaoException {

        Money mon = service.getMoney();
        if (mon.getAmount().compareTo(new BigDecimal("0")) > 0) {
            String change = service.giveChange(mon);
            view.getChange(change);
        }
    }
}
