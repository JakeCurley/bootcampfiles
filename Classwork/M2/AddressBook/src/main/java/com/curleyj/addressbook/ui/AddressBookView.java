/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook.ui;

import com.curleyj.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author Jake
 */
public class AddressBookView {
    UserIO io = new UserIOConsoleImpl();
    
    public AddressBookView() {

    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Address");
        io.print("2. Delete Address");
        io.print("3. Find Address");
        io.print("4. List Address Count");
        io.print("5. List All Addresses");
        io.print("6. Edit Address");
        
        return io.readInt("Please select the operation you wish to perform", 1, 6);
    }
    
    public Address getNewAddressInfo() {
        String lastName = io.readString("Please enter last name");
        String firstName = io.readString("Please enter first name");
        String address = io.readString("Please enter the address");
        
        Address currentAddress = new Address();
        currentAddress.setLastName(lastName);
        currentAddress.setFirstName(firstName);
        currentAddress.setAddress(address);
        
        return currentAddress;
    }
    
    public void displayNewAddressBanner() {
        io.print("=== Add New Address ===");
    }
    
    public void displayNewAddressSuccessBanner() {
        io.print("Address was successfully added.  Please hit enter to continue.");
    }
    
    public void listAllAddresses(List<Address> addressList) {
        for (Address currentAddress : addressList) {
            io.print(currentAddress.getFirstName() + " " + currentAddress.getLastName() + "\n" + currentAddress.getAddress());
        }
    }
    
    public void displayListAllBanner() {
        io.print("=== List All Addresses ===");
    }
    
    public void displayFindAddressBanner() {
        io.print("=== Find Address ===");
    }
    
    public String getLastNameChoice() {
        return io.readString("Please enter last name");
    }
    
    public void listFoundAddress(Address address) {
        if (address != null) {
            io.print(address.getFirstName() + " " + address.getLastName()+ "\n" + address.getAddress());
        }
        else {
            io.print("No such name found.");
        }
        io.readString("Please hit enter to continue");
    }
    
    public void displayDeleteAddressBanner() {
        io.print("=== DELETE ADDRESS ===");
    }
    
    public void displayDeleteSuccessBanner() {
        io.readString("Address successfully removed.  Please hit enter to continue.");
                
    }
    
    public void addressCount(int count) {
        io.print("Total number of addresses in the book is " + count);
    }
    
    public void displayAddressCountBanner() {
        io.print("=== ADDRESS COUNT ===");
    }
    
    public void displayEditAddressBanner() {
        io.print("=== EDIT ADDRESS ===");
    }
    
    public void displayEditSuccessBanner() {
        io.print("The address was edited successfully.  Please hit enter to continue.");
    }
    
    public String editAddress() {
        String edit = io.readString("Enter the new address.");
        return edit;
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
