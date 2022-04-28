package parkingfloor;

public class ParkingFloor {
    private int floorSpace;
    private int[] busSlots;
    private int[] bikeSlots;
    private int[] carSlots;

    public void setFloorSpace(int floorSpace) {
        this.floorSpace = floorSpace;
    }
    public void setBikeSlots(int bikeCapacity) {
        this.bikeSlots = new int[bikeCapacity];
    }
    public void setBusSlots(int busCapacity) {
        this.busSlots = new int[busCapacity];
    }
    public void setCarSlots(int carCapacity) {
        this.carSlots = new int[carCapacity];
    }

    public int getFloorSpace() {
        return floorSpace;
    }
    public int[] getBikeSlots() {
        return bikeSlots;
    }
    public int[] getBusSlots() {
        return busSlots;
    }
    public int[] getCarSlots() {
        return carSlots;
    }


}
