/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.classroster;

import com.curleyj.classroster.controller.ClassRosterController;
import com.curleyj.classroster.dao.ClassRosterAuditDao;
import com.curleyj.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.curleyj.classroster.dao.ClassRosterDao;
import com.curleyj.classroster.dao.ClassRosterDaoFileImpl;
import com.curleyj.classroster.service.ClassRosterServiceLayer;
import com.curleyj.classroster.service.ClassRosterServiceLayerImpl;
import com.curleyj.classroster.ui.ClassRosterView;
import com.curleyj.classroster.ui.UserIO;
import com.curleyj.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
        
        controller.run();
    }
}
