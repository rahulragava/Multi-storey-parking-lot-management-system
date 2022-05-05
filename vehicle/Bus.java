package vehicle;

public class Bus implements Vehicle {
    String vehicleNumber;
    public static int busFirstHourParkingPrice = 100;
    public static int busRemainingHourParkingPrice =150;

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
        return busFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return busRemainingHourParkingPrice;
    }


}
