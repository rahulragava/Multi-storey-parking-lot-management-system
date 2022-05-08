package customer;

import vehicle.*;

public class CustomerController {
    private final CustomerView view;
    private final Customer customer;
    private Vehicle vehicle;
    private int visitedTimes;

    public CustomerController(Customer customer, CustomerView view){
        this.customer = customer;
        this.view = view;
    }

    public void setVisitedTimes(int visitedTimes) {
        this.visitedTimes = visitedTimes;
        this.customer.setVisitedTimes(this.visitedTimes);
    }

    public void interact(){
        this.view.interaction();
        this.customer.setName(this.view.name);
        this.customer.setMobileNumber(this.view.mobileNumber);
        this.customer.setVehicle(this.view.vehicle);

    }

    public String getCustomerName(){
        return this.customer.getName();
    }
    public String getCustomerContactNumber(){
        return this.customer.getMobileNumber();
    }
    public Vehicle getVehicleType(){
        return this.customer.getVehicle();
    }
    public int getVisitedTimes() { return visitedTimes; }
}
