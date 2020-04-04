/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.vendingmachine.app;

import com.curleyj.vendingmachine.controller.VendingMachineController;
import com.curleyj.vendingmachine.dao.VendingMachineDao;
import com.curleyj.vendingmachine.dao.VendingMachineDaoAudit;
import com.curleyj.vendingmachine.dao.VendingMachineDaoAuditFileImpl;
import com.curleyj.vendingmachine.dao.VendingMachineDaoException;
import com.curleyj.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.curleyj.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.curleyj.vendingmachine.service.VendingMachineInvalidSelectionException;
import com.curleyj.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.curleyj.vendingmachine.service.VendingMachineServiceLayer;
import com.curleyj.vendingmachine.service.VendingMachineServiceLayerFileImpl;
import com.curleyj.vendingmachine.ui.UserIO;
import com.curleyj.vendingmachine.ui.UserIOFileImpl;
import com.curleyj.vendingmachine.ui.VendingMachineView;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) throws VendingMachineDaoException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineInvalidSelectionException, IOException {
        
        //UserIO myIo = new UserIOFileImpl();
        //VendingMachineView myView = new VendingMachineView(myIo);
        //VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        //VendingMachineDaoAudit myAuditDao = new VendingMachineDaoAuditFileImpl();
        //VendingMachineServiceLayer myService = new VendingMachineServiceLayerFileImpl(myDao, myAuditDao);
        //VendingMachineController controller = new VendingMachineController(myService, myView);
        //controller.run();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        
        controller.run();
    }

}
