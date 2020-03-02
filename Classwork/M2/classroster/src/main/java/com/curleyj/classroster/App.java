/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.classroster;

import com.curleyj.classroster.controller.ClassRosterController;
import com.curleyj.classroster.dao.ClassRosterDao;
import com.curleyj.classroster.dao.ClassRosterDaoFileImpl;
import com.curleyj.classroster.ui.ClassRosterView;
import com.curleyj.classroster.ui.UserIO;
import com.curleyj.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        
        ClassRosterView myView = new ClassRosterView(io);
        
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterController controller = new ClassRosterController(myDao, myView);
        controller.run();
    }
}
