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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jake
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private Item onlyItem;
    private Money onlyMoney = new Money("current");
    private Map<String, Item> itemList = new HashMap<>();
    private Map<String, Money> mapMoney = new HashMap<>();
    
    public VendingMachineDaoStubImpl() {
        onlyItem.setName("KitKat");
        onlyItem.setQuantity(3);
        onlyItem.setPrice(new BigDecimal("1.10"));
        onlyItem.setID(1);
        
        onlyMoney.setAmount(new BigDecimal("1.10"));
    }
    
    @Override
    public Map<String, Item> getMap() {
        return itemList;
    }
    
    @Override
    public Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException {
        if (onlyMoney.getName() == null) {
            return null;
        }
        else {
            itemList.put(onlyItem.getName(), onlyItem);
            return mapMoney;
        }
    }
    @Override
    public ArrayList<Item> displayItems() throws VendingMachineDaoException{
        return new ArrayList<Item>(itemList.values());
    }
    @Override
    public Money getMoney() {
        return onlyMoney;
    }
    @Override
    public Item itemSelection(String choice) {
        if (choice.equals("1")){
            return onlyItem;
        }
        else {
            return null;
        }
    }
    @Override
    public Item updateItems(Item choiceItem) throws VendingMachineDaoException {
        return onlyItem;
    }

    @Override
    public void writeRoster() throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
