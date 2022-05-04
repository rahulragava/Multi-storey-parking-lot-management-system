package vehicle;

public class Bus implements Vehicle {
    String vehicleNumber;
    int busFirstHourParkingPrice = 100;
    int busRemainingHourParkingPrice =150;

    public Bus(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getVehicleNumber() {
        return this.vehicleNumber;

    }

    @Override
    public String getVehicleType() {
        return "bus";
    }

    @Override
    public int getVehicleFirstHourParkingPrice() {
        return this.busFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return this.busRemainingHourParkingPrice;
    }


}
