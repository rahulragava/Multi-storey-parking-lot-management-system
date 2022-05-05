package vehicle;

public class Car implements Vehicle {
    public static int carFirstHourParkingPrice = 80;
    public static int carRemainingParkingHourPrice = 100;
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
        return carFirstHourParkingPrice;
    }

    @Override
    public int getVehicleRemainingHourParkingPrice() {
        return carRemainingParkingHourPrice;
    }

}
