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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jake
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer service;
    
    public FlooringMasteryServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
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
    }

    /**
     * Test of validateStateGetTaxRate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateStateGetTaxRate() throws Exception {
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = service.getMap();
        
        order order1 = newMap.get(1);
        order1.setState("OH");
        service.validateStateGetTaxRate(newMap.get(1));
        
        newMap = service.getMap();
        assertEquals(newMap.get(1).getTaxRate(), new BigDecimal("6.25"));
                
    }

    /**
     * Test of validateProductGetCosts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testValidateProductGetCosts() throws Exception {
        String type = "wood";
        product newProduct = new product(type);
       
        TreeMap<Integer, order> newMap = new TreeMap<>();
        newMap = service.getMap();
        order order1 = newMap.get(1);
        order1.setProductType(newProduct.getName());
        service.validateProductGetCosts(order1);
        newMap = service.getMap();
        assertEquals(newMap.get(1).getCpsq(),new BigDecimal("5.15"));
        assertEquals(newMap.get(1).getLaborCPSQ(), new BigDecimal("4.75"));
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
        newMap = service.getMap();
        
        assertEquals(newMap.get(1).getCustomerName(), "Curley");
        assertNull(newMap.get(500));
    }

    /**
     * Test of addToMap method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddToMap() throws Exception {
        order order = new order(3);
        //order.setDate("03172020");
        service.addToMap(order);
        
        TreeMap<Integer,order> newMap = new TreeMap<>();
        newMap = service.getMap();
        
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
            order = service.validateOrderNumber(newMap, 500);
            fail("should have thrown exception");
            }
            catch(FlooringMasteryInvalidOrderException e) {
                return;
            }

            order = service.validateOrderNumber(newMap, 1);
            assertNotNull(order);

    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceException {
        order order = new order(3);
        order.setDate("03172020");
        service.addToMap(order);
        TreeMap<Integer, order> newMap = new TreeMap<>();
        
        newMap = service.getMap();
        assertEquals(newMap.get(3), order);
        
        service.removeOrder(order);
        
        newMap = service.getMap();
        assertEquals(newMap.get(3), null);   
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
        
        assertFalse(service.getMode());
        
    }
}
