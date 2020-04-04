/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook.controller;

import com.curleyj.addressbook.dao.AddressBookDao;
import com.curleyj.addressbook.dao.AddressBookDaoException;
import com.curleyj.addressbook.dao.AddressBookDaoFileImpl;
import com.curleyj.addressbook.dto.Address;
import com.curleyj.addressbook.ui.AddressBookView;
import com.curleyj.addressbook.ui.UserIO;
import com.curleyj.addressbook.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Jake
 */
public class AddressBookController {
    
    AddressBookView view = new AddressBookView();
    AddressBookDao dao = new AddressBookDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    
    public void run() {
        try {
            boolean keepGoing = true;
            int menuSelection = 0;

            while(keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addAddress();
                        break;
                    case 2:
                        deleteAddress();
                        break;
                    case 3:
                        findAddress();
                        break;
                    case 4:
                        addressCount();
                        break;
                    case 5:
                        listAllAddresses();
                        break;
                    case 6:
                        editAddress();
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");

                }
            }
            io.print("GOOD BYE");
        }
        catch (AddressBookDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

        private int getMenuSelection() {

            return view.printMenuAndGetSelection();
        }

        public void addAddress() throws AddressBookDaoException {
            view.displayNewAddressBanner();
            Address newAddress = view.getNewAddressInfo();
            dao.addAddress(newAddress.getLastName(), newAddress);
            view.displayNewAddressSuccessBanner();
        }

        private void listAllAddresses() throws AddressBookDaoException {
            view.displayListAllBanner();
            List<Address> addressList = dao.listAllAddresses();
            view.listAllAddresses(addressList);
        }

        private void findAddress() throws AddressBookDaoException {
            view.displayFindAddressBanner();
            String lastName = view.getLastNameChoice();
            Address address = dao.getAddress(lastName);
            view.listFoundAddress(address);
        }

        private void deleteAddress() throws AddressBookDaoException {
            view.displayDeleteAddressBanner();
            String lastName = view.getLastNameChoice();
            dao.deleteAddress(lastName);
            view.displayDeleteSuccessBanner();
        }

        private void addressCount() throws AddressBookDaoException {
            view.displayAddressCountBanner();
            List<Address> addressCount = dao.listAllAddresses();
            int count = addressCount.size();
            view.addressCount(count);
        }

        private void editAddress() {
            view.displayEditAddressBanner();
            String lastName = view.getLastNameChoice();
            String edit = view.editAddress();
            Address editAddress = dao.editAddress(lastName);
            editAddress.setAddress(edit);
            view.displayEditSuccessBanner();
        }
}