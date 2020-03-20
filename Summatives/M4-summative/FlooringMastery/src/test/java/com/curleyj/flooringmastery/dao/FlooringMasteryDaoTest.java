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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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
public class FlooringMasteryDaoTest {
    
    FlooringMasteryDao dao;
    public FlooringMasteryDaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("dao", FlooringMasteryDao.class);
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
        newOrder.setDate("03182020");
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
        newOrder2.setDate("03192020");
        dao.addToMap(newOrder2);
        
        dao.writeOrders();
        
    }
    
    @AfterEach
    public void tearDown() throws FlooringMasteryPersistenceException {
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = dao.getMapDao();
        
        dao.removeOrder(newMap.get(1));
        dao.removeOrder(newMap.get(2));
        
        newMap = dao.getMapDao();
        dao.writeOrders();
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
        tearDown();
    }

    /**
     * Test of loadOrdersByDate method, of class FlooringMasteryDao.
     */
    @Test
    public void testLoadOrdersByDate1() throws Exception {
        setUp();
        String date = "03182020";
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
        String date = "03192020";
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
        try {
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
        catch (FlooringMasteryPersistenceException e) {
            fail("Did not find the file.");
        }
    }

    /**
     * Test of loadProducts method, of class FlooringMasteryDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testLoadProducts() throws Exception {
        setUp();
        product newProduct = dao.loadProducts("Wood");
        
        assertEquals(newProduct.getCpsf(), new BigDecimal("5.15"));
        assertEquals(newProduct.getLaborCPSF(), new BigDecimal("4.75"));

        product newProduct2 = dao.loadProducts("test");
        assertNull(newProduct2);
        
        tearDown();
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
        newMap = dao.getMapDao();
        
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
        assertNotEquals(newMap.get(1).getProductType(), "Carpet");
        
        tearDown();
    }

    /**
     * Test of writeLibrary method, of class FlooringMasteryDao.
     */
    /*@Test
    public void testWriteLibrary() throws Exception {
        //writeLibrary works in setUp();
        
        setUp();
        TreeMap<Integer, order> orderMap = new TreeMap<>();
        order order = new order(1);
        orderMap.put(order.getOrderNumber(), order);
        try {                                                   //Testing an order with no date - should fail
            PrintWriter out;
            Set<Integer> orderKey = orderMap.keySet();
            for (Integer k : orderKey) {
                String date = orderMap.get(k).getDate();
                if (date == null) {
                    throw new FlooringMasteryPersistenceException("No date.");
                }
                File file = new File("./resources/Orders_" + date + ".txt");
                out = new PrintWriter(new FileWriter(file, false));
                for (Integer j : orderKey) {
                    if (orderMap.get(j).getDate().equals(date)) {
                        String orderAsText;
                        //orderAsText = marshallItem(orderMap.get(j));
                        //out.println(orderAsText);
                        out.flush();
                    }
                }
            }
            fail("Should have thrown exception");
        }
        catch (FlooringMasteryPersistenceException e) {
            return;
        }
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
        order order = dao.validateOrderNumber(newMap, 1);
        try {
            order order1 = dao.validateOrderNumber(newMap, 500);
            fail("Should have thrown exception.");
        }
        catch (FlooringMasteryInvalidOrderException e) {
            return;
        }
        
        assertEquals(order, newMap.get(1));
        
        tearDown();
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
        setUp();
        String ROSTER_FILE = "test";
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
            fail("Should have thrown exception");
        } catch (FileNotFoundException e) {
            //throw new FlooringMasteryPersistenceException("-_- Could not load item data into memory.", e);
        }
        tearDown();
    }
    @Test
    public void testLoadCounter2() throws Exception {
        setUp();
        dao.loadCounter();
        
       counter counter = dao.getCurrentCounter();
       assertNotNull(counter);
       
       tearDown();
    }

    /**
     * Test of writeCounter method, of class FlooringMasteryDao.
     */
    @Test
    public void testWriteCounter() throws Exception {
        setUp();
        //store value so it can be set back after check
        dao.loadCounter();
        counter counter3 = dao.getCurrentCounter();
        counter counter4 = new counter("test");
        counter4.setCount(counter3.getCount());
        
        counter counter = dao.getCurrentCounter();
        counter.setCount(20);
        dao.writeCounter(counter);
        counter counter2 = dao.getCurrentCounter();
        assertEquals(counter, counter2);
        
        counter3.setCount(counter4.getCount());
        counter3.setName("counter");
        dao.writeCounter(counter3);
        
        tearDown();
    }


    /**
     * Test of setMode method, of class FlooringMasteryDao.
     */
    @Test
    public void testSetMode() throws Exception {
        setUp();
        
        boolean check = dao.setMode();
        
        assertFalse(check);
        
        tearDown();
        
    }
    
}
