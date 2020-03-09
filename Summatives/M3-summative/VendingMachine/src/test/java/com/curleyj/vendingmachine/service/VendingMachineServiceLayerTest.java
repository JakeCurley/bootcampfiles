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

/**
 *
 * @author Jake
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineDaoAudit auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceLayerFileImpl(dao, auditDao);
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
    public void testAddMoney() throws Exception {
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
    public void testDisplayItems() throws Exception {
    }

    /**
     * Test of itemSelection method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testItemSelection() throws Exception {
        
        String choice = "6";
        
        try{
            Item newItem = service.itemSelection(choice);
            fail("VendingMachineInvalidSelectionException was not thrown.");
        }
        catch (VendingMachineInvalidSelectionException e) {
            return;
        }
    }

    /**
     * Test of updateMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateMoney() throws Exception {
    }

    /**
     * Test of inventoryCheck method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInventoryCheck() throws Exception {
    }

    /**
     * Test of giveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGiveChange() throws Exception {
    }

    /**
     * Test of updateItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testUpdateItems() throws Exception {
    }

    public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

        public Map<String, Money> addMoney(BigDecimal money) throws VendingMachineDaoException {
            return null;
        }

        public Money getMoney() {
            return null;
        }

        public ArrayList<Item> displayItems() throws VendingMachineDaoException {
            return null;
        }

        public Item itemSelection(String choice) throws VendingMachineInvalidSelectionException {
            return null;
        }

        public Money updateMoney(Item choiceItem, Money money) throws VendingMachineInsufficientFundsException, VendingMachineDaoException {
            return null;
        }

        public boolean inventoryCheck(Item item) throws VendingMachineNoItemInventoryException {
            return false;
        }

        public String giveChange(Money money) throws VendingMachineDaoException {
            return "";
        }

        public void updateItems(Item choiceItem) throws VendingMachineDaoException {
        }
    }
    
}
