package App;

import customer.*;
import parkingfloor.*;
import parkingticket.*;
import transaction.*;

import java.util.*;

public class Main {
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Enter the number of floors: ");
        int numberOfFloors = in.nextInt();
        in.nextLine();
        HashMap<Integer, ParkingFloor> floors = new HashMap<>();
        for(int i = 0; i < numberOfFloors; i++){
            ParkingFloor floor = new ParkingFloor();
            ParkingFloorView floorView = new ParkingFloorView();
            ParkingFloorController floorController = new ParkingFloorController(floor, floorView);
            floorController.interact();
            floors.put(i,floor);
        }
        ArrayList<Customer> allCustomer = new ArrayList<>();
        ArrayList<Ticket> allTicket = new ArrayList<>();
        CustomerView customerView;
        Customer customer;
        CustomerController customerController;
        Ticket ticket;
        TicketView ticketView;
        TicketController ticketController = null;
        TransactionView transactionView;
        TransactionController transactionController;
        int rememberCustomerIndex = -1;
        int hoursInDay = 24;

        while(true)          //not yet finished completely
        {
            showInteraction();
            System.out.println("Enter the choice: (1/2/3/4/5)");
            int choice = in.nextInt();
            switch(choice)
            {
                case 1: //showing the available slots in each floors
                    showSlots(floors);
                    System.out.println(BLUE + "Press -1 to go back" + RESET);
                    int goBack = in.nextInt();
                    in.nextLine();
                    if(goBack == -1)
                        break;

                case 2: //entering customer details. and book a slot
                   customerView = new CustomerView();
                   customer = new Customer();
                   customerController = new CustomerController(customer, customerView);
                    customerController.interact();
                    allCustomer.add(customer);
                   int visitedTimes = Collections.frequency(allCustomer,customer);
                   customer.setVisitedTimes(visitedTimes);
                    rememberCustomerIndex+=1;
                    break;

                case 3: //generate (entry/exit) ticket
                    showMenu();
                    System.out.println("(Entry/Exit) ----------> (1/2)");
                    int choose = in.nextInt();
                    boolean isWorking = true;
                    while(isWorking){

                        switch(choose){
                            case 1 :
                                ticket = new Ticket();
                                ticketView = new TicketView();
                                ticketController = new TicketController(ticket, ticketView, allCustomer.get(rememberCustomerIndex), floors);
                                if(ticketController.getSlotNumber() == null){
                                    System.out.println("Sorry, there is no spot available for your vehicle");
                                    allCustomer.remove(rememberCustomerIndex);
                                    rememberCustomerIndex--;
                                }
                                else{
                                    ticketController.setEntryTicket();
                                    allTicket.add(ticket);
                                    ticketController.showTicket();
                                }
                                isWorking = false;
                                break;
                            case 2:
                                allTicket.get(rememberCustomerIndex).setExitTime();
                                isWorking = false;
                                break;
                            default:
                                System.out.println("Invalid choice try again");
                                break;
                        }
                    }
                    break;

                case 4: //reservation for a day
                    ticket = new Ticket();
                    ticketView = new TicketView();
                    ticketController = new TicketController(ticket, ticketView, allCustomer.get(rememberCustomerIndex), floors, hoursInDay);
                    if(ticketController.getSlotNumber() == null){
                        System.out.println("Sorry, there is no spot available for your vehicle");
                        allCustomer.remove(rememberCustomerIndex);
                        rememberCustomerIndex--;
                    }
                    else {
                        ticketController.setEntryTicket();
                        ticket.setExitTime();
                        allTicket.add(ticket);
                        ticketController.showTicket();
                    }
                    break;

                case 5: //transaction         ..... not yet completed
                    transactionView = new TransactionView();
                    transactionController = new TransactionController(transactionView,
                            allCustomer.get(rememberCustomerIndex), allTicket.get(rememberCustomerIndex));

            }

        }
    }

    private static void showSlots(HashMap<Integer, ParkingFloor> floors) {
        String leftAlignFormat = "| %-9s | %-9d | %-10d | %-9d|%n";

        System.out.format("+-----------+-----------+------------+----------+%n");
        System.out.format("| Floor no. | Car Slots | Bike Slots | Bus Slots|%n");
        System.out.format("+-----------+-----------+------------+----------+%n");
        Iterator<Integer> itr = floors.keySet().iterator();
        floors.forEach(
                (floorNumber, floorDetail) ->
                        System.out.format(leftAlignFormat, floorNumber, floorDetail.getCarSlots().length,
                                floorDetail.getBikeSlots().length, floorDetail.getBusSlots().length));
        System.out.format("+-----------+-----------+------------+----------+%n");

    }

    public static void showInteraction(){
        System.out.println("+---------------------------------+");
        System.out.println("| 1. See available slots          |");
        System.out.println("| 2. Customer details             |");
        System.out.println("| 3. (Entry/Exit) ticket          |");
        System.out.println("| 4. Reserve a slot for whole day |");
        System.out.println("| 5. Transaction                  |");
        System.out.println("+---------------------------------+");
    }

    public static void showMenu(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
        System.out.println("*         1. entry ticket                       * ");
        System.out.println("*         2. exit  ticket                       * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");

    }
}
