/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary;

import com.curleyj.dvdlibrary.controller.DvDLibraryController;
import com.curleyj.dvdlibrary.dao.DvDLibraryDao;
import com.curleyj.dvdlibrary.dao.DvDLibraryDaoFileImpl;
import com.curleyj.dvdlibrary.ui.DvDLibraryView;
import com.curleyj.dvdlibrary.ui.UserIO;
import com.curleyj.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Jake
 */
public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DvDLibraryView myView = new DvDLibraryView(myIo);
        DvDLibraryDao myDao = new DvDLibraryDaoFileImpl();
        DvDLibraryController controller = new DvDLibraryController(myDao, myView);
        controller.run();
    }
}
