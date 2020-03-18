/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.dvdlibrary.dao;

import com.curleyj.dvdlibrary.dto.dvd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jake
 */
public class DvDLibraryDaoTest {
    
    private DvDLibraryDao dao = new DvDLibraryDaoFileImpl();
    private Map<String, dvd> dvds = new HashMap<>();
    
    public DvDLibraryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws DvDLibraryDaoException {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws DvDLibraryDaoException {
        List<dvd> dvdList = dao.getAllDvds();
        
        for (dvd dvd : dvdList) {
            dao.removeDvd(dvd.getTitle());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvDLibraryDao.
     */
    @Test
    public void testAddDvd() throws Exception {
       
        dvd dvd = new dvd("Title1");
        dvd.setReleaseDate("2004");
        dvd.setDirectorName("Director1");
        dvd.setStudio("Studio1");
        dvd.setUserRating("Good movie");
        
        dao.addDvd(dvd.getTitle(), dvd);
        
        dvd fromDao = dao.listDvdInfo(dvd.getTitle());
        
        assertEquals(dvd, fromDao);
        
    }

    /**
     * Test of getAllDvds method, of class DvDLibraryDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        dvd dvd1 = new dvd("Title1");
        dvd1.setReleaseDate("2004");
        dvd1.setDirectorName("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setUserRating("Good movie");
        
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        dvd dvd2 = new dvd("Title2");
        dvd2.setReleaseDate("2001");
        dvd2.setDirectorName("Director2");
        dvd2.setStudio("Studio2");
        dvd2.setUserRating("Bad movie");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        assertEquals(2, dao.getAllDvds().size());
        
    }

    /**
     * Test of listDvdInfo method, of class DvDLibraryDao.
     */
    @Test
    public void testListDvdInfo() throws DvDLibraryDaoException {
        
        dvd dvd1 = new dvd("Title1");
        dvd1.setReleaseDate("2004");
        dvd1.setDirectorName("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setUserRating("Good movie");
        
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        dvd newDvd = dao.listDvdInfo(dvd1.getTitle());
        
        assertEquals(dvd1, newDvd);
        
    }

    /**
     * Test of removeDvd method, of class DvDLibraryDao.
     */
    @Test
    public void testRemoveDvd() throws Exception {
        
        dvd dvd1 = new dvd("Title1");
        dvd1.setReleaseDate("2004");
        dvd1.setDirectorName("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setUserRating("Good movie");
        
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        dvd dvd2 = new dvd("Title2");
        dvd2.setReleaseDate("2001");
        dvd2.setDirectorName("Director2");
        dvd2.setStudio("Studio2");
        dvd2.setUserRating("Bad movie");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        dao.removeDvd(dvd1.getTitle());
        
        assertEquals(1, dao.getAllDvds().size());
        assertNull(dao.listDvdInfo(dvd1.getTitle()));
        
        dao.removeDvd(dvd2.getTitle());
        
        assertEquals(0, dao.getAllDvds().size());
        assertNull(dao.listDvdInfo(dvd2.getTitle()));
        
    }

    /**
     * Test of editDvd method, of class DvDLibraryDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testEditDvd() throws Exception {
        dvd dvd1 = new dvd("Title1");
        dvd1.setReleaseDate("2004");
        dvd1.setDirectorName("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setUserRating("Good movie");
        
        String editChoice = "1";
        String edit = "2009";
        
        dvd fromDao = dao.editDvd(dvd1.getTitle(), editChoice, edit);
        
        assertNotEquals(dvd1, fromDao);
        
    }

    /**
     * Test of titleCheck method, of class DvDLibraryDao.
     */
    @Test
    public void testTitleCheck() throws DvDLibraryDaoException {
        
        dvd dvd1 = new dvd("Title1");
        dvd1.setReleaseDate("2004");
        dvd1.setDirectorName("Director1");
        dvd1.setStudio("Studio1");
        dvd1.setUserRating("Good movie");
        
        dvds.put(dvd1.getTitle(), dvd1);
        
        dvd dvd2 = new dvd("Title2");
        dvd2.setReleaseDate("2001");
        dvd2.setDirectorName("Director2");
        dvd2.setStudio("Studio2");
        dvd2.setUserRating("Bad movie");
        
        dvds.put(dvd2.getTitle(), dvd2);
        
        assertTrue(dvds.containsKey(dvd1.getTitle()));
        dvds.remove(dvd1.getTitle());
        
        assertFalse(dvds.containsKey(dvd1.getTitle()));
        
    }

}
