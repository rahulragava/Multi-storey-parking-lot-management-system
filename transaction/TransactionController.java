package transaction;

import customer.Customer;
import parkingticket.Ticket;

public class TransactionController {
    private final TransactionView transactionView;
    private final Customer customer;
    private final Ticket ticket;
    private double totalAmount;

    public TransactionController(TransactionView transactionView, Customer customer, Ticket ticket) {
        this.transactionView = transactionView;
        this.customer = customer;
        this.ticket = ticket;
        System.out.println();
    }

    public void showTicket(){
        this.transactionView.generateTicket(this.ticket.getSlotNumber(),this.customer.getVehicle().getVehicleNumber(),
                this.ticket.getEntryTime(),this.ticket.getExitTime(),this.totalAmount);
    }

    public void parkingBill(){   //not completed yet
        double discountedPrice;
        double amount = this.customer.getVehicle().getVehicleFirstHourParkingPrice()
                + this.customer.getVehicle().getVehicleRemainingHourParkingPrice()
                * ((Integer.parseInt(this.ticket.getExitTime().substring(0,2))
                - Integer.parseInt(this.ticket.getEntryTime().substring(0,2))) - 1);

        if(((Integer.parseInt(this.ticket.getExitTime().substring(3,5)) - Integer.parseInt(this.ticket.getEntryTime().substring(3,5)))) > 15){
            amount +=  this.customer.getVehicle().getVehicleRemainingHourParkingPrice();
        }

        if (customer.getVisitedTimes() == 1){
            discountedPrice = (double)((this.customer.getVehicle().getVehicleFirstHourParkingPrice() * 50) / 100)
                    + (double)((this.customer.getVehicle().getVehicleRemainingHourParkingPrice() * 10) / 100);
        }
        else{
            discountedPrice = 0;
        }
        this.totalAmount = amount + discountedPrice;
    }

}
