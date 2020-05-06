/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook.dao;

import com.curleyj.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author Jake
 */
public interface AddressBookDao {
    Address addAddress(String lastName, Address address) throws AddressBookDaoException;
    
    Address deleteAddress(String lastName) throws AddressBookDaoException;
    
    Address getAddress(String address) throws AddressBookDaoException;
    
    List listAllAddresses() throws AddressBookDaoException;
    
    Address editAddress(String lastName);
}
