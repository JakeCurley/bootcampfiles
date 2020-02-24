package com.curleyj.classmodeling;

/**
 *
 * @author Jake
 */
public class House1 {
    private String streetName;
    private int houseNumber;
    private int distanceAway;
    private double timeToArrive;
    private String directionToTravel;
    private String methodOfTransport;
    
    public House1() {
        
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getDistanceAway() {
        return distanceAway;
    }

    public void setDistanceAway(int distanceAway) {
        this.distanceAway = distanceAway;
    }
    
    public double timeToArrive(int distanceAway, String methodOfTravel) {
        
    }
    public double getTimeToArrive() {
        return timeToArrive;
    }

    public void setTimeToArrive(double timeToArrive) {
        this.timeToArrive = timeToArrive;
    }

    public String getDirectionToTravel() {
        return directionToTravel;
    }

    public void setDirectionToTravel(String directionToTravel) {
        this.directionToTravel = directionToTravel;
    }

    public String getMethodOfTransport() {
        return methodOfTransport;
    }

    public void setMethodOfTransport(String methodOfTransport) {
        this.methodOfTransport = methodOfTransport;
    }
    
    
}
