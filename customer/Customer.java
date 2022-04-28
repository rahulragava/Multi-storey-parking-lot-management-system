package customer;

import vehicle.Vehicle;

public class Customer {
    private String name;
    private String mobileNumber;
    private Vehicle vehicle;
    private int visitedTimes;

    //setters
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setVisitedTimes(int visitedTimes) { this.visitedTimes = visitedTimes; }

    //getters
    public String getName() {
        return this.name;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public Vehicle getVehicle() {
        return this.vehicle;
    }
    public int getVisitedTimes() {
        return visitedTimes;
    }
}
