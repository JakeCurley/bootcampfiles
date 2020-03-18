/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.service;

import com.curleyj.vendingmachine.dao.VendingMachineDao;
import com.curleyj.vendingmachine.dao.VendingMachineDaoAudit;
import com.curleyj.vendingmachine.dao.VendingMachineDaoException;
import com.curleyj.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.curleyj.vendingmachine.dto.Item;
import com.curleyj.vendingmachine.dto.Money;
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
public class VendingMachineServiceLayerTest {
    
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
        //VendingMachineDao dao = new VendingMachineDaoStubImpl();
        //VendingMachineDaoAudit auditDao = new VendingMachineAuditDaoStubImpl();
        //service = new VendingMachineServiceLayerFileImpl(dao, auditDao);
        
         ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
         service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testAddMoney() {
    }

    /**
     * Test of getMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetMoney() {
    }

    /**
     * Test of displayItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testDisplayItems() {
    }

    /**
     * Test of itemSelection method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testItemSelection() {
        
        String choice = "6";
        
        try{
            Item newItem = service.itemSelection(choice);
            fail("VendingMachineInvalidSelectionException was not thrown.");
        }
        catch (VendingMachineInvalidSelectionException e) {
            System.out.println("works");
            return;
        }
    }

    /**
     * Test of updateMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateMoney() throws VendingMachineInsufficientFundsException, VendingMachineDaoException {
        
        Item onlyItem = new Item("KitKat");
        onlyItem.setPrice(new BigDecimal("1.00"));
        Money money = new Money("money");
        money.setAmount(new BigDecimal(".50"));
        
        try {
           service.updateMoney(onlyItem, money);
           fail("Should have thrown exception");
        }
        catch (VendingMachineInsufficientFundsException e) {
            System.out.println("Works");
            return;
        }
        
    }

    /**
     * Test of inventoryCheck method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInventoryCheck() {
    }

    /**
     * Test of giveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGiveChange() {
    }

    /**
     * Test of updateItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateItems() {
    }
    
}
