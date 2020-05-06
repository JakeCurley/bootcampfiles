/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.addressbook;

import com.curleyj.addressbook.controller.AddressBookController;

/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) {
        AddressBookController controller = new AddressBookController();
        controller.run();
    }
}
