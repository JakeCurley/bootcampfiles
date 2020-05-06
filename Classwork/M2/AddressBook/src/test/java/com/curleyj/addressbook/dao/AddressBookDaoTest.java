/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook.dao;

import com.curleyj.addressbook.dto.Address;
import java.util.List;
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
public class AddressBookDaoTest {
    
    private AddressBookDao dao = new AddressBookDaoFileImpl();
    
    public AddressBookDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws AddressBookDaoException {
        List<Address> addressList = dao.listAllAddresses();
        
        for (Address address : addressList) {
            dao.deleteAddress(address.getLastName());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddAddress() throws Exception {
        setUp();
        
        Address address = new Address();
        address.setLastName("Black");
        address.setFirstName("Ryan");
        address.setAddress("567 Third St.");
        
        dao.addAddress(address.getLastName(), address);
        
        Address fromDao = dao.getAddress(address.getLastName());
        
        assertEquals(address, fromDao); 
    }

    /**
     * Test of deleteAddress method, of class AddressBookDao.
     * @throws java.lang.Exception
     */
    @org.junit.jupiter.api.Test
    public void testDeleteAddress() throws Exception {
       /* setUp();
        
        Address address1 = new Address("Smith");
        address1.setFirstName("Joe");
        address1.setAddress("123 First St.");
        
        dao.addAddress(address1.getLastName(), address1);
        
        Address address2 = new Address("Jones");
        address2.setFirstName("Sally");
        address2.setAddress("321 2nd St.");
        
        dao.addAddress(address2.getLastName(), address2);
        
        dao.deleteAddress(address1.getLastName());
        assertEquals(1, dao.listAllAddresses().size());
        assertNull(dao.getAddress(address1.getLastName()));
        
        dao.deleteAddress(address2.getLastName());
        assertEquals(0, dao.listAllAddresses().size());
        assertNull(dao.getAddress(address2.getLastName()));*/
    } 

    /**
     * Test of getAddress method, of class AddressBookDao.
     */
    @Test
    public void testGetAddress() throws Exception {
        setUp();
        
        Address address1 = new Address("Smith");
        address1.setFirstName("Joe");
        address1.setAddress("123 First St.");
        
        dao.addAddress(address1.getLastName(), address1);
        
        Address address2 = new Address("Jones");
        address2.setFirstName("Sally");
        address2.setAddress("321 2nd St.");
        
        dao.addAddress(address2.getLastName(), address2);
        
        assertEquals(2, dao.listAllAddresses().size());
        
    }

    /**
     * Test of listAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void testListAllAddresses() throws Exception {
    }

    /**
     * Test of editAddress method, of class AddressBookDao.
     */
    @Test
    public void testEditAddress() {
    }
    
}
