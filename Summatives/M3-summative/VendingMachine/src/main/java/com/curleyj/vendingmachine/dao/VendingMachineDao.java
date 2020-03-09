/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.dao;

import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jake
 */
public interface VendingMachineDao {
    
    Map<String, Item> getMap();
    Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException;
    ArrayList<Item> displayItems() throws VendingMachineDaoException;
    Money getMoney();
    Item itemSelection(String choice);
    Item updateItems(Item choiceItem) throws VendingMachineDaoException;
    void writeRoster() throws VendingMachineDaoException;
}
