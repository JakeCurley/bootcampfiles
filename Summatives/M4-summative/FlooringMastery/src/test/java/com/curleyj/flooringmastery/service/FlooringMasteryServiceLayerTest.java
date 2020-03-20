/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.service;

import com.curleyj.flooringmastery.dao.FlooringMasteryDao;
import com.curleyj.flooringmastery.dao.FlooringMasteryDaoStubImpl;
import com.curleyj.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.TreeMap;
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
public class FlooringMasteryServiceLayerTest {
    
    FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
    FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayerFileImpl(dao);
    
    public FlooringMasteryServiceLayerTest() {
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
     * Test of loadOrders method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testLoadOrders() throws Exception {
        //not needed to test
    }

    /**
     * Test of displayOrdersService method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testDisplayOrdersService() throws Exception {
        String date = "03172020";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, dateFormat);
        
        HashMap<Integer, order> newMap = new HashMap<>();
        newMap = dao.loadOrdersByDate(ld);
        
        if (newMap.get(1).getCustomerName().equals("Curley")) {
            return;
        }
        if (newMap.get(1).getCustomerName().equals("Curley2")) {
            fail("Should not get this order.");
        }
        
        
    }

    /**
     * Test of validateStateGetTaxRate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateStateGetTaxRate() throws Exception {
        String state = "OH";
        BigDecimal taxRate = dao.loadTaxes(state);
        assertEquals(taxRate, new BigDecimal("6.25"));
        
        String state2 = "IN";
        BigDecimal taxRate2 = dao.loadTaxes(state2);
        assertEquals(taxRate2, new BigDecimal("6.00"));
    }

    /**
     * Test of validateProductGetCosts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateProductGetCosts() throws Exception {
        String type = "wood";
        product newProduct = dao.loadProducts(type);
        
        assertEquals(newProduct.getCpsf(),new BigDecimal("5.15"));
        assertEquals(newProduct.getLaborCPSF(), new BigDecimal("4.75"));

    }

    /**
     * Test of moneyCalculations method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testMoneyCalculations() {
        order newOrder = new order(5);
        newOrder.setCustomerName("Curley");
        newOrder.setState("IN");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("wood");
        newOrder.setCpsq(new BigDecimal("5.15"));
        newOrder.setLaborCPSQ(new BigDecimal("4.75"));
        newOrder.setArea(new BigDecimal("30.00"));
        
        newOrder.setLaborCost((newOrder.getLaborCPSQ().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setMaterialCost((newOrder.getCpsq().multiply(newOrder.getArea())).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTax((newOrder.getLaborCost().add(newOrder.getMaterialCost())).multiply(newOrder.getTaxRate().movePointLeft(2)).setScale(2, RoundingMode.HALF_UP));
        newOrder.setTotal((newOrder.getLaborCost().add(newOrder.getMaterialCost()).add(newOrder.getTax())).setScale(2, RoundingMode.HALF_UP));
        
        assertEquals(newOrder.getLaborCost(), new BigDecimal("142.50"));
        assertEquals(newOrder.getMaterialCost(), new BigDecimal("154.50"));
        assertEquals(newOrder.getTax(), new BigDecimal("17.82"));
        assertEquals(newOrder.getTotal(), new BigDecimal("314.82"));
    }

    /**
     * Test of getMap method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetMap() {
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = dao.getMapDao();
        
        assertEquals(newMap.get(1).getCustomerName(), "Curley");
    }

    /**
     * Test of addToMap method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddToMap() throws Exception {
        order order = new order(3);
        order.setDate("03172020");
        dao.addToMap(order);
        
        TreeMap<Integer,order> newMap = new TreeMap<>();
        newMap = dao.getMapDao();
        
        assertEquals(newMap.get(3), order);
        
    }

    /**
     * Test of getOrderNumberByDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetOrderNumberByDate() throws Exception {
        HashMap<Integer, order> newMap = new HashMap<>();
        
        order order = new order(1);
        newMap.put(order.getOrderNumber(), order);
        
        try {
            order = dao.getOrderNumberByDate(newMap, 2);
            fail("should have thrown exception");
        }
        catch (FlooringMasteryInvalidOrderException e ) {
            return;
        }
        
        try {
            dao.getOrderNumberByDate(newMap, 1);
            return;
        }
        catch (FlooringMasteryInvalidOrderException e) {
            fail("That order number should have been in the map.");
        }
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceException {
        order order = new order(3);
        order.setDate("03172020");
        dao.addToMap(order);
        TreeMap<Integer, order> newMap = new TreeMap<>();
        
        newMap = dao.getMapDao();
        assertEquals(newMap.get(3), order);
        
        dao.removeOrder(order);
        
        newMap = dao.getMapDao();
        assertEquals(newMap.get(3), null);
        assertEquals(newMap.get(2).getOrderNumber(), 2);
        
    }

    /**
     * Test of saveCurrentWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveCurrentWork() throws Exception {
        //not needed to test = tested in dao
    }

    /**
     * Test of getCounter method, of class FlooringMasteryServiceLayer.
     */
   /* @Test
    public void testGetCounter() throws Exception {
        counter counter = dao.loadCounter();
        
        assertEquals(counter.getCount(), 3);
    }

    /**
     * Test of saveCounter method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveCounter() throws Exception {
        // not needed = tested in dao
    }

    /**
     * Test of getMode method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetMode() throws Exception {
        
        assertTrue(dao.setMode());
        
    }
}
