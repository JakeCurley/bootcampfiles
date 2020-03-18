/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.service;

import com.curleyj.vendingmachine.dao.VendingMachineDao;
import com.curleyj.vendingmachine.dao.VendingMachineDaoAudit;
import com.curleyj.vendingmachine.dao.VendingMachineDaoException;
import com.curleyj.vendingmachine.dto.Change;
import static com.curleyj.vendingmachine.dto.Change.DIME;
import static com.curleyj.vendingmachine.dto.Change.NICKEL;
import static com.curleyj.vendingmachine.dto.Change.PENNY;
import static com.curleyj.vendingmachine.dto.Change.QUARTER;
import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Jake
 */
public class VendingMachineServiceLayerFileImpl implements VendingMachineServiceLayer{
    VendingMachineDao dao;
    private VendingMachineDaoAudit auditDao;
    
    public VendingMachineServiceLayerFileImpl(VendingMachineDao dao, VendingMachineDaoAudit auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException {
       Map<String, Money> newMap = dao.addMoney(money);
       auditDao.writeAuditEntry("$" + money + " was added to the vending machine.");
       return newMap;
    }
        
    @Override
    public Money getMoney() {
        return dao.getMoney();    
    }
    
    @Override
    public ArrayList<Item> displayItems() throws VendingMachineDaoException {
        return dao.displayItems();
    }
    
    @Override
    public Item itemSelection(String choice) throws VendingMachineInvalidSelectionException {
        Item itemChoice = dao.itemSelection(choice);
        if(itemChoice == null) {
            throw new VendingMachineInvalidSelectionException("Please use a valid number for selection.");
        }
        return dao.itemSelection(choice);
    }
    
    @Override
    public Money updateMoney(Item choiceItem, Money money) throws VendingMachineInsufficientFundsException, VendingMachineDaoException {
        BigDecimal broke = new BigDecimal("0");
        BigDecimal price = choiceItem.getPrice();
        BigDecimal currentMoney = money.getAmount();
        BigDecimal afterPurchase = currentMoney.subtract(price).setScale(2, RoundingMode.HALF_UP);
        if (afterPurchase.compareTo(broke) < 0) {
            money.setAmount(money.getAmount());
            throw new VendingMachineInsufficientFundsException("Insufficient funds for this transaction.");
        }
        else {
            money.setAmount(afterPurchase);
            auditDao.writeAuditEntry(choiceItem.getName() + " was purchased.");
        }
        return money;
    }
    @Override
    public boolean inventoryCheck(Item item) throws VendingMachineNoItemInventoryException {
        if (item.getQuantity() < 1) {
            throw new VendingMachineNoItemInventoryException("I'm sorry that item is out of stock.");
        }
        else {
            return true;
        }
    }
    
    @Override
    public String giveChange(Money money) throws VendingMachineDaoException {
        int iQuarter = 0;
        int iDime = 0;
        int iNickel = 0;
        int iPenny = 0;
        BigDecimal min = new BigDecimal("0");
        BigDecimal mon = money.getAmount().movePointRight(2);
         
        while (mon.compareTo(min) > 0) {
            
            if (mon.compareTo(chooseChange(QUARTER))>= 0) {
                mon = mon.subtract(chooseChange(QUARTER));
                iQuarter++;
            }

            else if (mon.compareTo(chooseChange(DIME))>= 0) {
                mon = mon.subtract(chooseChange(DIME));
                iDime++;
            }

            else if (mon.compareTo(chooseChange(NICKEL))>= 0) {
                mon = mon.subtract(chooseChange(NICKEL));
                iNickel++;
            }

            else {
                mon = mon.subtract(chooseChange(PENNY));
                iPenny++;
            }
        }
        String totalChange = "Your change is " + iQuarter + " quarters " + iDime + " dimes " + iNickel + " nickels " + iPenny + " pennies.";
        auditDao.writeAuditEntry("Change dispursed: " + iQuarter + " quarters " + iDime + " dimes " + iNickel + " nickels " + iPenny + " pennies.");
        return totalChange; 
    }
    
    public BigDecimal chooseChange(Change change) {
        BigDecimal a = new BigDecimal("0");
        switch (change) {
            case QUARTER:
                a = new BigDecimal("25");
                break;
            case DIME:
                a = new BigDecimal("10");
                break;
            case NICKEL:
                a = new BigDecimal("5");
                break;
            case PENNY:
                a = new BigDecimal("1");
                break;    
        }
        return a;
    }
    
    @Override
    public void updateItems(Item choiceItem) throws VendingMachineDaoException {
        dao.updateItems(choiceItem);
    }
}
