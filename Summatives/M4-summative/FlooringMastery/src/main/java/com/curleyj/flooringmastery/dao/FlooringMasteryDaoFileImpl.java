/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curleyj.flooringmastery.dao;

import com.curleyj.flooringmastery.dto.counter;
import com.curleyj.flooringmastery.dto.order;
import com.curleyj.flooringmastery.dto.product;
import com.curleyj.flooringmastery.dto.taxes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Jake
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    HashMap<String, taxes> taxesMap = new HashMap<>();
    HashMap<Integer, order> orderMap = new HashMap<>();
    HashMap<String, product> productsMap = new HashMap<>();
    public static final String DELIMITER = ",";

    private order unmarshallOrder(String orderAsText, String date) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        String temp = orderTokens[0];
        int orderNumber = Integer.parseInt(temp);

        order orderFromFile = new order(orderNumber);

        String customerName = orderTokens[1];

        String state = orderTokens[2];

        String temp3 = orderTokens[3];
        BigDecimal taxRate = new BigDecimal(temp3);

        String productType = orderTokens[4];

        String temp4 = orderTokens[5];
        BigDecimal area = new BigDecimal(temp4);

        String temp5 = orderTokens[6];
        BigDecimal CostPerSquareFoot = new BigDecimal(temp5);

        String temp6 = orderTokens[7];
        BigDecimal laborCostPerSquareFoot = new BigDecimal(temp6);

        String temp7 = orderTokens[8];
        BigDecimal materialCost = new BigDecimal(temp7);

        String temp8 = orderTokens[9];
        BigDecimal laborCost = new BigDecimal(temp8);

        String temp9 = orderTokens[10];
        BigDecimal tax = new BigDecimal(temp9);

        String temp10 = orderTokens[11];
        BigDecimal total = new BigDecimal(temp10);

        orderFromFile.setOrderNumber(orderNumber);
        orderFromFile.setCustomerName(customerName);
        orderFromFile.setState(state);
        orderFromFile.setTaxRate(taxRate);
        orderFromFile.setProductType(productType);
        orderFromFile.setArea(area);
        orderFromFile.setCpsq(CostPerSquareFoot);
        orderFromFile.setLaborCPSQ(laborCostPerSquareFoot);
        orderFromFile.setMaterialCost(materialCost);
        orderFromFile.setLaborCost(laborCost);
        orderFromFile.setTax(tax);
        orderFromFile.setTotal(total);
        orderFromFile.setDate(date);

        return orderFromFile;
    }

    private taxes unmarshallTaxes(String taxesAsText) {
        String[] orderTokens = taxesAsText.split(DELIMITER);

        String state = orderTokens[0];

        taxes taxesFromFile = new taxes(state);

        String taxrate = orderTokens[1];
        BigDecimal taxRate = new BigDecimal(taxrate);

        taxesFromFile.setTaxRate(taxRate);

        return taxesFromFile;
    }

    private product unmarshallProducts(String productsAsText) {
        String[] orderTokens = productsAsText.split(DELIMITER);

        String productType = orderTokens[0];

        product productsFromFile = new product(productType);

        String costPer = orderTokens[1];
        BigDecimal Cpsf = new BigDecimal(costPer);
        productsFromFile.setCpsf(Cpsf);

        String laborCostPer = orderTokens[2];
        BigDecimal laborCPSF = new BigDecimal(laborCostPer);
        productsFromFile.setLaborCPSF(laborCPSF);

        return productsFromFile;
    }

    @Override
    public void loadOrders() throws Exception {
        File folder = new File("./resources/");
        File[] listOfFiles = folder.listFiles();
        int max = 0;
        Scanner scanner;
        for (File file : listOfFiles) {
            String date = file.toString().substring(19, 27);
            System.out.println(date);
            if (file.isFile()) {
                try {
                    scanner = new Scanner(new BufferedReader(new FileReader(file)));
                } catch (FileNotFoundException e) {
                    throw new Exception("-_- Could not load item data into memory.", e);
                }

                String currentLine;
                order currentOrder;
                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();
                    currentOrder = unmarshallOrder(currentLine, date);
                    orderMap.put(currentOrder.getOrderNumber(), currentOrder);
                }

                scanner.close();
            }
        }
    }

    @Override
    public HashMap<Integer, order> loadOrdersByDate(LocalDate ld) {
        String date = ld.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        System.out.println(date);
        HashMap<Integer, order> newMap = new HashMap<>();
        Set<Integer> orderKey = orderMap.keySet();
        for (Integer k : orderKey) {
            if (orderMap.get(k).getDate().equals(date)) {
                String test = orderMap.get(k).getDate();
                if (test.equals(date)) {
                    newMap.put(k, orderMap.get(k));
                }
            }
        }
        return newMap;
    }

    @Override
    public HashMap<Integer, order> getMapDao() {
        return orderMap;
    }

    @Override
    public boolean setMode() throws Exception {
        String ROSTER_FILE = "configuration.txt";
        Scanner scanner;
        boolean training = false;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load mode settings into memory.", e);
        }
        String currentLine = scanner.nextLine();
        if (currentLine.equalsIgnoreCase("training")) {
            training = true;
        }

        return training;
    }

    @Override
    public BigDecimal loadTaxes(String state) throws Exception {

        String ROSTER_FILE = "Taxes.txt";
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load taxes data into memory.", e);
        }

        String currentLine;
        taxes currentTax;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTaxes(currentLine);
            taxesMap.put(currentTax.getState(), currentTax);
        }

        scanner.close();
        taxes newTax = taxesMap.get(state);
        return newTax.getTaxRate();
    }

    @Override
    public product loadProducts(String type) throws Exception {

        String ROSTER_FILE = "Products.txt";
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load item data into memory.", e);
        }

        String currentLine;
        product currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProducts(currentLine);
            productsMap.put(currentProduct.getName(), currentProduct);
        }
        scanner.close();
        product newProduct = productsMap.get(type);
        return newProduct;
    }

    @Override
    public void addToMap(order newOrder) throws Exception {
        orderMap.put(newOrder.getOrderNumber(), newOrder);
    }

    private String marshallItem(order aOrder) {

        String orderAsText = aOrder.getOrderNumber() + DELIMITER;
        orderAsText += aOrder.getCustomerName() + DELIMITER;
        orderAsText += aOrder.getState() + DELIMITER;
        orderAsText += aOrder.getTaxRate() + DELIMITER;
        orderAsText += aOrder.getProductType() + DELIMITER;
        orderAsText += aOrder.getArea() + DELIMITER;
        orderAsText += aOrder.getCpsq() + DELIMITER;
        orderAsText += aOrder.getLaborCPSQ() + DELIMITER;
        orderAsText += aOrder.getMaterialCost() + DELIMITER;
        orderAsText += aOrder.getLaborCost() + DELIMITER;
        orderAsText += aOrder.getTax() + DELIMITER;
        orderAsText += aOrder.getTotal();

        return orderAsText;
    }

    private counter unmarshallCounter(String counterAsText) {
        String[] orderTokens = counterAsText.split(DELIMITER);

        String name = orderTokens[0];

        counter counterFromFile = new counter(name);

        String stringCount = orderTokens[1];
        int count = Integer.parseInt(stringCount);

        counterFromFile.setCount(count);

        return counterFromFile;
    }

    public String marshallCounter(counter counter) {
        String counterAsText = counter.getName() + DELIMITER;
        int counter1 = counter.getCount();
        String count = Integer.toString(counter1);

        counterAsText += count;
        return counterAsText;
    }

    @Override
    public counter loadCounter() throws Exception {

        String ROSTER_FILE = "counter.txt";
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load item data into memory.", e);
        }

        String currentLine;
        counter currentCounter;

        currentLine = scanner.nextLine();
        currentCounter = unmarshallCounter(currentLine);
        scanner.close();

        return currentCounter;
    }

    @Override
    public void writeCounter(counter counter) throws Exception {
        PrintWriter out;
        File file = new File("counter.txt");
        out = new PrintWriter(new FileWriter(file));
        counter.setCount(counter.getCount() + 1);
        String counterAsText;
        counterAsText = marshallCounter(counter);
        out.println(counterAsText);
        out.flush();
        out.close();
    }

    @Override
    public void writeLibrary() throws Exception {

        PrintWriter out;

        Set<Integer> orderKey = orderMap.keySet();

        for (Integer k : orderKey) {
            String date = orderMap.get(k).getDate();
            File file = new File("./resources/Orders_" + date + ".txt");
            out = new PrintWriter(new FileWriter(file, false));
            for (Integer j : orderKey) {
                if (orderMap.get(j).getDate().equals(date)) {
                    String orderAsText;
                    orderAsText = marshallItem(orderMap.get(j));
                    out.println(orderAsText);
                    out.flush();
                }
            }

        }
    }

    @Override
    public order getOrderNumberByDate(HashMap<Integer, order> newMap, int orderNumber) {
        return newMap.get(orderNumber);
    }

    @Override
    public void removeOrder(order newOrder) {
        orderMap.remove(newOrder.getOrderNumber());
    }
}
