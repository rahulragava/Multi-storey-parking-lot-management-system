package parkingticket;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ticket {
    private String slotNumber;
    private String vehicleNumber;
    private String entryTime;
    private String exitTime;

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public void setExitTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        this.exitTime =  sdf.format(cal.getTime());
    }

    public String getSlotNumber(){
        return this.slotNumber;
    }
    public String getEntryTime() {
        return entryTime;
    }
    public String getExitTime() {
        return exitTime;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }


}
