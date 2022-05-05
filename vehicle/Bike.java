package vehicle;

public class Bike implements Vehicle {
    String vehicleNumber;
    public static int bikeFirstHourParkingPrice = 50;
    public static int bikeRemainingHourParkingPrice = 70;

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
        return bikeFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return bikeRemainingHourParkingPrice;
    }

}
