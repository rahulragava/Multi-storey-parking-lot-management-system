package parkingticket;

import customer.Customer;
import parkingfloor.ParkingFloor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TicketController {
    Ticket ticket;
    TicketView ticketView;
    Customer customer;
    HashMap<Integer, ParkingFloor> floors;
    int dayHour;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public TicketController(Ticket ticket, TicketView ticketView, Customer customer, HashMap<Integer, ParkingFloor> floors) {
        this.ticket = ticket;
        this.customer = customer;
        this.floors = floors;
        this.ticketView = ticketView;
    }

    public TicketController(Ticket ticket, TicketView ticketView, Customer customer, HashMap<Integer, ParkingFloor> floors, int dayHour) {
        this.ticket = ticket;
        this.customer = customer;
        this.floors = floors;
        this.ticketView = ticketView;
        this.dayHour = dayHour;
    }

    public void setEntryTicket(String parkingSlotNumber){
        this.ticket.setSlotNumber(parkingSlotNumber);
        this.ticket.setEntryTime(getEntryTime());
        this.ticket.setVehicleNumber(this.customer.getVehicle().getVehicleNumber());
    }

    public void showTicket(){
        if(dayHour == 24) {
            this.ticketView.generateTicket(ticket.getSlotNumber(), ticket.getVehicleNumber(), ticket.getEntryTime(), ticket.getEntryTime());
        }
        else{
            this.ticketView.generateTicket(ticket.getSlotNumber(), ticket.getVehicleNumber(), ticket.getEntryTime(), "--:--:--");
        }
    }

    public String getSlotNumber(){
        if (customer.getVehicle().getVehicleType().equalsIgnoreCase("bike")){
            var floorsSet = floors.keySet();
            for(int i = 0; i < floorsSet.size(); i++){
                int[] slot = floors.get(i).getBikeSlots();
                for (int j = 0; j < slot.length; j++){
                   if(slot[j] == 0){
                       slot[j] = 1;
                       return "BI" + "0" + i + "0" + j;
                   }
                }
            }
        }
        else if(customer.getVehicle().getVehicleType().equalsIgnoreCase("car")){
            var floorsSet = floors.entrySet();
            for(int i = 0; i < floorsSet.size(); i++){
                int[] slot = floors.get(i).getCarSlots();
                for (int j = 0; j < slot.length; j++){
                    if(slot[j] == 0){
                        slot[j] = 1;
                        return "CA" + "0" + i + "0" + j;
                    }
                }
            }
        }
        else{
            var floorsSet = floors.entrySet();
            for(int i = 0; i < floorsSet.size(); i++){
                int[] slot = floors.get(i).getBusSlots();
                for (int j = 0; j < slot.length; j++){
                    if(slot[j] == 0){
                        slot[j] = 1;
                        return "BU" + "0" + i + "0" + j;
                    }
                }
            }
        }
        return null;
    }

    public String getEntryTime(){
        return sdf.format(cal.getTime());
    }

}
