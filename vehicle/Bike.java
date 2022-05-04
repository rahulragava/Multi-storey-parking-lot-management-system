package vehicle;

public class Bike implements Vehicle {
    String vehicleNumber;
    int bikeFirstHourParkingPrice = 50;
    int bikeRemainingHourParkingPrice = 70;

    public Bike(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }
    @Override
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    @Override
    public String getVehicleType() {
        return "bike";
    }

    @Override
    public int getVehicleFirstHourParkingPrice() {
        return this.bikeFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return this.bikeRemainingHourParkingPrice;
    }

}
