/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dao;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.dto.taxes;
import com.curleyj.flooringmastery.service.FlooringMasteryInvalidOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;
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
public class FlooringMasteryDaoTest {
    
    FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    public FlooringMasteryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        dao.loadOrders();
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = dao.getMapDao();
        Set<Integer> orderKey = newMap.keySet();
        for (Integer k : orderKey) {
            dao.removeOrder(newMap.get(k));
            dao.writeLibrary();
        }
        
        order newOrder = new order(1);
        newOrder.setCustomerName("Curley");
        newOrder.setState("IN");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("30.00"));
        newOrder.setCpsq(new BigDecimal("5.15"));
        newOrder.setLaborCPSQ(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("154.50"));
        newOrder.setLaborCost(new BigDecimal("142.50"));
        newOrder.setTax(new BigDecimal("17.82"));
        newOrder.setTotal(new BigDecimal("314.82"));
        newOrder.setDate("03172020");
        dao.addToMap(newOrder);
        
        order newOrder2 = new order(2);
        newOrder2.setCustomerName("Curley");
        newOrder2.setState("OH");
        newOrder2.setTaxRate(new BigDecimal("6.25"));
        newOrder2.setProductType("Carpet");
        newOrder2.setCpsq(new BigDecimal("2.25"));
        newOrder2.setLaborCPSQ(new BigDecimal("2.1"));
        newOrder2.setMaterialCost(new BigDecimal("67.50"));
        newOrder2.setLaborCost(new BigDecimal("63.00"));
        newOrder2.setTax(new BigDecimal("8.16"));
        newOrder2.setTotal(new BigDecimal("138.66"));
        newOrder2.setArea(new BigDecimal("30.00"));
        newOrder2.setDate("03182020");
        dao.addToMap(newOrder2);
        
        dao.writeLibrary();
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of loadOrders method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadOrders() throws Exception {
        setUp();

        TreeMap<Integer, order> testMap = new TreeMap<>();
        dao.loadOrders();
        testMap = dao.getMapDao();
        Set<Integer> orderKey = testMap.keySet();
        assertEquals(testMap.get(1).getOrderNumber(), 1);
        assertEquals(testMap.get(2).getOrderNumber(), 2);
       
    }

    /**
     * Test of loadOrdersByDate method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadOrdersByDate1() throws Exception {
        setUp();
        String date = "03172020";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, dateFormat);
        
        HashMap<Integer, order> test1Map = new HashMap<>();
        
        test1Map = dao.loadOrdersByDate(ld);
        Set<Integer> orderKey = test1Map.keySet();
        for (Integer k : orderKey) {
            assertEquals(test1Map.get(k).getOrderNumber(), 1);
        } 
    }
      @Test
    public void testLoadOrdersByDate2() throws Exception {
        setUp();
        String date = "03182020";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, dateFormat);
        
        HashMap<Integer, order> test1Map = new HashMap<>();
        
        test1Map = dao.loadOrdersByDate(ld);
        Set<Integer> orderKey = test1Map.keySet();
        for (Integer k : orderKey) {
            assertEquals(test1Map.get(k).getOrderNumber(), 2);
        } 
    }

    /**
     * Test of getMapDao method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetMapDao() {
        //Don't need to test -> working in loadOrders() test
    }

    /**
     * Test of loadTaxes method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadTaxes() throws Exception {
        setUp();
        taxes newTaxes = new taxes("IN");
        newTaxes.setTaxRate(dao.loadTaxes("IN"));
        assertEquals(newTaxes.getTaxRate(), new BigDecimal("6.00"));
        taxes newTaxes2 = new taxes("OH");
        newTaxes2.setTaxRate(dao.loadTaxes("OH"));
        assertEquals(newTaxes2.getTaxRate(), new BigDecimal("6.25"));
        taxes newTaxes3 = new taxes("MI");
        newTaxes3.setTaxRate(dao.loadTaxes("MI"));
        assertEquals(newTaxes3.getTaxRate(), new BigDecimal("5.75"));
        taxes newTaxes4 = new taxes("PA");
        newTaxes4.setTaxRate(dao.loadTaxes("PA"));
        assertEquals(newTaxes4.getTaxRate(), new BigDecimal("6.75"));
    }

    /**
     * Test of loadProducts method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadProducts() throws Exception {
        setUp();
        product newProduct = dao.loadProducts("Wood");
        
        assertEquals(newProduct.getCpsf(), new BigDecimal("5.15"));
        assertEquals(newProduct.getLaborCPSF(), new BigDecimal("4.75"));
    }

    /**
     * Test of addToMap method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddToMap() throws Exception {
        setUp();
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = dao.getMapDao();
        order remove = newMap.get(1);
        dao.removeOrder(remove);
        
        order newOrder = new order(1);
        String date = "03182020";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, dateFormat);
        
        newOrder.setCustomerName("Curley");
        newOrder.setState("IN");
        newOrder.setTaxRate(new BigDecimal("6.00"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("30.00"));
        newOrder.setCpsq(new BigDecimal("5.15"));
        newOrder.setLaborCPSQ(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("154.50"));
        newOrder.setLaborCost(new BigDecimal("142.50"));
        newOrder.setTax(new BigDecimal("17.82"));
        newOrder.setTotal(new BigDecimal("314.82"));
        newOrder.setDate("03172020");
        dao.addToMap(newOrder);
        dao.addToMap(newOrder);

        newMap = dao.getMapDao();
        
        assertEquals(newMap.get(1), newOrder);
        
    }

    /**
     * Test of writeLibrary method, of class FlooringMasteryDao.
     */
    @Test
    public void testWriteLibrary() throws Exception {
        //testing writeLibrary() used in setup
        setUp();
        TreeMap<Integer, order> newMap = new TreeMap<>();
        
        newMap = dao.getMapDao();
        
        assertEquals(newMap.get(1).getOrderNumber(), 1);
    }

    /**
     * Test of getOrderNumberByDate method, of class FlooringMasteryDao.
     */
    @Test
    public void testGetOrderNumberByDate() throws Exception {
        setUp();
        HashMap<Integer, order> newMap = new HashMap<>();
        TreeMap<Integer, order> testMap = new TreeMap<>();
        
        testMap = dao.getMapDao();
        newMap.putAll(testMap);
        order order = dao.getOrderNumberByDate(newMap, 1);
        try {
            order order1 = dao.getOrderNumberByDate(newMap, 5);
            fail("Should have thrown exception.");
        }
        catch (FlooringMasteryInvalidOrderException e) {
            return;
        }
        
        assertEquals(order, newMap.get(1));
        
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() {
        //Already works in previous tests
    }

    /**
     * Test of loadCounter method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadCounter() throws Exception {
        counter counter = dao.loadCounter();
        
        assertNotEquals(counter.getCount(), null);
    }

    /**
     * Test of writeCounter method, of class FlooringMasteryDao.
     */
    @Test
    public void testWriteCounter() throws Exception {
        
        //store value so it can be set back after check
        counter counter3 = dao.loadCounter();
        
        counter counter = new counter("counter");
        counter.setCount(20);
        dao.writeCounter(counter);
        counter counter2 = dao.loadCounter();
        assertEquals(counter2.getCount(), counter.getCount());
        
        counter3.setCount(counter3.getCount()-1);
        dao.writeCounter(counter3);
    }

    /**
     * Test of setMode method, of class FlooringMasteryDao.
     */
    @Test
    public void testSetMode() throws Exception {
        
        boolean check = dao.setMode();
        
        assertFalse(check);
        
    }
    
}
