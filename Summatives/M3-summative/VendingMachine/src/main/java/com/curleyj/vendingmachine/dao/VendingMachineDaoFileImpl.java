/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.dao;

import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    
    public static final String ROSTER_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Item> items = new HashMap<>();
    private Map<String, Money> moneyMap = new HashMap<>();
    
    public Map<String, Item> getMap() {
        return items;
    }
    @Override
    public Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException {
        
            try {
                Money mon = moneyMap.get("current");
                mon.setAmount(mon.getAmount().add(money));
                moneyMap.put("current", mon);
            }
            catch (NullPointerException e) {
               Money mon = new Money("current");
               mon.setAmount(money);
               moneyMap.put("current", mon);
            }
       return moneyMap;
    }
    
    @Override
    public Money getMoney() {
       Money money = moneyMap.get("current");
       return money;
    }
    
    @Override
    public ArrayList<Item> displayItems() throws VendingMachineDaoException {
        loadRoster();
        return new ArrayList<Item>(items.values());
    }
    
    @Override
    public Item itemSelection(String choice) {
        Item itemChoice = items.get(choice);
 
                switch (choice) {
                    case "1":
                        itemChoice = items.get("KitKat");
                        break;
                    case "2":
                        itemChoice = items.get("Snickers");
                        break;
                    case "3":
                        itemChoice = items.get("Twix");
                        break;
                    case "4":
                        itemChoice = items.get("PayDay");
                        break;
                    case "5":
                        itemChoice = items.get("Herseys"); 
                        break;
                    default:
                        itemChoice = null;
                }
                
        return itemChoice;
    }
    
    private String marshallItem(Item aItem) {
        String itemAsText = aItem.getName() + DELIMITER;
        itemAsText += aItem.getQuantity() + DELIMITER;
        itemAsText += aItem.getPrice() + DELIMITER;
        itemAsText += aItem.getID();
        
        return itemAsText;
    }
    
    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        
        String name = itemTokens[0];
        
        Item itemFromFile = new Item(name);
        
        String temp = itemTokens[1];
        int quantity = Integer.parseInt(temp);
        
        String temp2 = itemTokens[2];
        BigDecimal price = new BigDecimal(temp2);
        
        String temp3 = itemTokens[3];
        int ID = Integer.parseInt(temp3);
        
        itemFromFile.setQuantity(quantity);
        itemFromFile.setPrice(price);
        itemFromFile.setID(ID);

        
        return itemFromFile;
    }
    
     private void loadRoster() throws VendingMachineDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }
        catch (FileNotFoundException e) {
            throw new VendingMachineDaoException("-_- Could not load item data into memory.", e);
        }
        
        String currentLine;
        
        Item currentItem;
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        
        scanner.close();
        
    }
     
    @Override
     public void writeRoster() throws VendingMachineDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        catch (IOException e){
            throw new VendingMachineDaoException("Could not save item data.", e);
        }
        
        String itemAsText;
        List<Item> itemList = this.displayItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        
        out.close();
    }
     
    @Override
    public Item updateItems(Item choiceItem) throws VendingMachineDaoException {
        choiceItem.setQuantity((choiceItem.getQuantity()) - 1);
        writeRoster();
        
        return choiceItem;
    }
    
}
