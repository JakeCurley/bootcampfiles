/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.dao;

import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
import com.curleyj.vendingmachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jake
 */
public class VendingMachineDaoTest {
    
    VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dao", VendingMachineDaoFileImpl.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        
        Map<String, Item> items = dao.getMap();
        Item item1 = new Item("KitKat");
        item1.setQuantity(3);
        item1.setPrice(new BigDecimal("1.10"));
        item1.setID(1);
        
        Item item2 = new Item("Snickers");
        item2.setQuantity(3);
        item2.setPrice(new BigDecimal(".95"));
        item2.setID(2);
        
        Item item3 = new Item("Twix");
        item3.setQuantity(3);
        item3.setPrice(new BigDecimal("1.05"));
        item3.setID(3);
        
        Item item4 = new Item("PayDay");
        item4.setQuantity(3);
        item4.setPrice(new BigDecimal(".75"));
        item4.setID(4);
        
        Item item5 = new Item("Herseys");
        item5.setQuantity(3);
        item5.setPrice(new BigDecimal("1.00"));
        item5.setID(5);
        
        items.put("KitKat", item1);
        items.put("Snickers", item2);
        items.put("Twix", item3);
        items.put("PayDay", item4);
        items.put("Herseys", item5);
        dao.writeRoster();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMoney method, of class VendingMachineDao.
     */
    @Test
    public void testAddMoney() throws Exception {
       
        Map<String, Money> mon = dao.addMoney(new BigDecimal("4.00"));
        
        Money fromDao = dao.getMoney();
        
        assertEquals(mon.get("current").getAmount(), fromDao.getAmount());
    }

    /**
     * Test of displayItems method, of class VendingMachineDao.
     */
    @Test
    public void testDisplayItems() throws Exception {
        
        setUp();
        ArrayList<Item> testList = dao.displayItems();
        
        assertEquals(testList.get(0).getName(), "KitKat");
        assertEquals(testList.get(0).getQuantity(), 3);
        assertEquals(testList.get(0).getPrice(), new BigDecimal("1.10"));
        assertEquals(testList.get(0).getID(), 1);
        assertEquals(testList.get(1).getName(), "Snickers");
        assertEquals(testList.get(2).getName(), "Twix");
        assertEquals(testList.get(3).getName(), "PayDay");
        assertEquals(testList.get(4).getName(), "Herseys");
        
    }

    /**
     * Test of itemSelection method, of class VendingMachineDao.
     */
    @Test
    public void testItemSelection() throws Exception {
        setUp();
        Item test = dao.itemSelection("1");
        
        assertEquals(test.getName(), "KitKat");
        assertEquals(test.getQuantity(), 3);
        assertEquals(test.getPrice(), new BigDecimal("1.10"));
        assertEquals(test.getID(), 1);
    }

    /**
     * Test of updateItems method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateItems() throws Exception {
        
        Item test = new Item("KitKat");
        test.setQuantity(3);
        Item item = dao.updateItems(test);
        
        assertEquals(item.getQuantity(), 2);
    }

}
