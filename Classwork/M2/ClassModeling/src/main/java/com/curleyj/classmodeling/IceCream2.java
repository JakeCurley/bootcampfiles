package com.curleyj.classmodeling;

/**
 *
 * @author Jake
 */
public class IceCream2 {
    private String[] flavors;
    private double costToPurchase;
    private double sellCost;
    private int inStock;
    private int demand;
    private double freezerSpace;
    
    public IceCream2() {
        
    }

    public String[] getFlavors() {
        return flavors;
    }

    public void setFlavors(String[] flavors) {
        this.flavors = flavors;
    }

    public double getCostToPurchase() {
        return costToPurchase;
    }

    public void setCostToPurchase(double costToPurchase) {
        this.costToPurchase = costToPurchase;
    }

    public double getSellCost() {
        return sellCost;
    }

    public void setSellCost(double sellCost) {
        this.sellCost = sellCost;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public double getFreezerSpace() {
        return freezerSpace;
    }

    public void setFreezerSpace(double freezerSpace) {
        this.freezerSpace = freezerSpace;
    }
    
    
}
