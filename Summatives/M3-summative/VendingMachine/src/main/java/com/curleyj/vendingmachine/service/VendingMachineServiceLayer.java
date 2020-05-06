/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.service;

import com.curleyj.vendingmachine.dao.VendingMachineDaoException;
import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jake
 */
public interface VendingMachineServiceLayer {
    Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException;
    Money getMoney();
    ArrayList<Item> displayItems() throws VendingMachineDaoException;
    Item itemSelection(String choice) throws VendingMachineInvalidSelectionException;
    Money updateMoney(Item choiceItem, Money money) throws VendingMachineInsufficientFundsException, VendingMachineDaoException;
    boolean inventoryCheck(Item item) throws VendingMachineNoItemInventoryException;
    String giveChange(Money money) throws VendingMachineDaoException;
    void updateItems(Item choiceItem) throws VendingMachineDaoException;
}
