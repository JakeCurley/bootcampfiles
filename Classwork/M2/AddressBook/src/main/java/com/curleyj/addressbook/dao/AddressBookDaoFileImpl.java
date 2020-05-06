/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook.dao;

import com.curleyj.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jake
 */
public class AddressBookDaoFileImpl implements AddressBookDao {
    
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookDaoException {
        loadRoster();
        Address newAddress = addresses.put(lastName, address);
        writeRoster();
        return newAddress;
    }

    @Override
    public Address deleteAddress(String lastName) throws AddressBookDaoException {
        //loadRoster();
        Address deletedAddress = addresses.remove(lastName);
       // writeRoster();
        return deletedAddress;
    }

    @Override
    public Address getAddress(String address) throws AddressBookDaoException {
       // loadRoster();
        return addresses.get(address);
    }

    @Override
    public List<Address> listAllAddresses() throws AddressBookDaoException {
        //loadRoster();
        return new ArrayList<Address>(addresses.values());
    }
    
    public Address editAddress(String lastName) {
        Address editAddress = addresses.get(lastName);
        return editAddress;
    }
    
    private Address unmarshallAddress(String addressAsText) {
        String[] addressTokens = addressAsText.split(DELIMITER);
        
        String lastName = addressTokens[0];
        Address addressFromFile = new Address(lastName);
        
        String firstName = addressTokens[1];
        String address = addressTokens[2];
        
        addressFromFile.setFirstName(addressTokens[1]);
        addressFromFile.setAddress(addressTokens[2]);
        
        return addressFromFile;
    }
    
    private void loadRoster() throws AddressBookDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        }
        
        catch (FileNotFoundException e) {
            throw new AddressBookDaoException("-_- Could not load roster data into memory.",e);
        }
        
        String currentLine;
        Address currentAddress;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentAddress = unmarshallAddress(currentLine);
            
            addresses.put(currentAddress.getLastName(), currentAddress);
        }
        scanner.close();
    }
    
    private String marshallAddress(Address aAddress) {
        String addressAsText = aAddress.getFirstName() + DELIMITER;
        addressAsText += aAddress.getLastName() + DELIMITER;
        addressAsText += aAddress.getAddress() + DELIMITER;
        
        return addressAsText;
    }
    
    private void writeRoster() throws AddressBookDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        }
        
        catch (IOException e) {
            throw new AddressBookDaoException("Could not save student data.", e);
        }
        
        String addressAsText;
        List<Address> addressList = this.listAllAddresses();
        
        for (Address currentAddress : addressList) {
            addressAsText = marshallAddress(currentAddress);
            out.println(addressAsText);
            out.flush();
        }
        out.close();
    }
}
