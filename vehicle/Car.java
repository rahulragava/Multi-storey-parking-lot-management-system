package vehicle;

public class Car implements Vehicle {
    int carFirstHourParkingPrice = 20;
    int carRemainingParkingHourPrice = 30;
    String vehicleNumber;

    public Car(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }
    @Override
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }


    @Override
    public int getVehicleFirstHourParkingPrice() {
        return this.carFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return this.carRemainingParkingHourPrice;
    }

}
