package customer;

import vehicle.*;

import java.util.Scanner;

public class CustomerView {
    Scanner in = new Scanner(System.in);
    String name,mobileNumber,vehicleType,vehicleNumber;
    Vehicle vehicle;
    void interaction(){
        System.out.print("Enter your Name: ");
              name = in.next();
        while(true){
            System.out.print("Enter your mobileNumber: ");
            mobileNumber = in.next();
            if(mobileNumber.length() == 10 &&
                    (mobileNumber.startsWith("9") || mobileNumber.startsWith("8") || mobileNumber.startsWith("7") ||
                            mobileNumber.startsWith("6"))){
                break;
            }
            else{
                System.out.println("The mobile number you provide is not valid, please try again...");
            }
        }
        System.out.print("The type of the vehicle you are going to park?(bus/car/bike)");
        while(true){
            vehicleType = in.next();
            if (vehicleType.equalsIgnoreCase("bus") ||
                    vehicleType.equalsIgnoreCase("bike") || vehicleType.equalsIgnoreCase("car")){
                System.out.print("Enter your vehicle number: ");
                vehicleNumber = in.next();
                break;
            }
            else{
                System.out.println("invalid input");
            }
        }
        vehicle = vehicleType.equalsIgnoreCase("bike") ?    //dip data binding
                new Bike(vehicleNumber) : vehicleType.equalsIgnoreCase("car") ? new Car(vehicleNumber) : new Bus(vehicleNumber);
    }
}
