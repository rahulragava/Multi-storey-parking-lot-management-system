package parkingfloor;


public class ParkingFloorController {
    ParkingFloor floor;
    ParkingFloorView floorView;

    public ParkingFloorController(ParkingFloor floor, ParkingFloorView floorView){
        this.floor = floor;
        this.floorView = floorView;
    }

    public void interact(){
        floorView.interaction();
        this.floor.setFloorSpace(this.floorView.spaces);
        this.floor.setBikeSlots((int)(this.floorView.spaces * 40 / 100));
        this.floor.setCarSlots((int)(this.floorView.spaces * 40 / 100));
        this.floor.setBusSlots((this.floorView.spaces - (this.getBikeCapacity().length + this.getCarCapacity().length)));
    }

    public int getFloorSpace(){
        return this.floor.getFloorSpace();
    }
    public int[] getBusCapacity(){
        return this.floor.getBusSlots();
    }
    public int[] getBikeCapacity(){
        return this.floor.getBikeSlots();
    }
    public int[] getCarCapacity(){
        return this.floor.getCarSlots();
    }

}
