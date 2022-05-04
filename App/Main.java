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
    static String adminName = "Rahul";
    static String adminPasswd = "rahulragava3";

    public static void main(String[] args){
        System.out.println("Enter the number of floors: ");
        int numberOfFloors = 0;
        boolean isMismatch = true;
        while(isMismatch){
            try {
                numberOfFloors = in.nextInt();
                isMismatch = false;
            }
            catch (InputMismatchException ime){
                System.out.println("invalid number");
                in.reset();
                in.next();
            }
        }
        in.nextLine();
        HashMap<Integer, ParkingFloor> floors = new HashMap<>();
        for(int i = 0; i < numberOfFloors; i++){
            ParkingFloor floor = new ParkingFloor();
            ParkingFloorView floorView = new ParkingFloorView();
            ParkingFloorController floorController = new ParkingFloorController(floor, floorView);
            floorController.interact();
            floors.put(i,floor);
        }
        HashMap<String, Customer> customers = new HashMap<>();
        HashMap<String, Ticket> tickets = new HashMap<>();
        CustomerView customerView;
        Customer customer = null;
        CustomerController customerController;
        Ticket ticket;
        TicketView ticketView;
        TicketController ticketController;
        TransactionView transactionView;
        TransactionController transactionController;
        ArrayList<Customer> allCustomer = new ArrayList<>();
        int rememberCustomerIndex = -1;
        int hoursInDay = 24;
        boolean closeParkingSystem = false;
        while(!closeParkingSystem)
        {
            showInteraction();
            System.out.println("Enter the choice: (1/2/3)");
            isMismatch = true;
            int choice = 0;
            while(isMismatch){
                try {
                    choice = in.nextInt();
                    isMismatch = false;
                }
                catch (InputMismatchException ime){
                    System.out.println("invalid number");
                    in.reset();
                    in.next();
                }
            }
            switch (choice) {
                case 1 -> { //showing the available slots in each floors
                    int goBack = 0;
                    while (goBack != -1) {
                        showSlots(floors);
                        System.out.println(BLUE + "Press -1 to go back" + RESET);
                        goBack = in.nextInt();
                        in.nextLine();
                    }
                }
                case 2 -> { //entering customer details. and book a slot
                    System.out.println("✨✨  Zoho's multi storey parking system welcomes you ✨✨");
                    boolean isEverythingOk = true;
                    int choose = 0;
                    while(isEverythingOk){
                        System.out.println("Here to park in ? (y/n)");
                        String isParkingIn = in.next();
                        if(isParkingIn.equalsIgnoreCase("y")){
                            choose = 1;
                            customerView = new CustomerView();
                            customer = new Customer();
                            customerController = new CustomerController(customer, customerView);
                            customerController.interact();
                            customers.put(customer.getMobileNumber(), customer);
                            allCustomer.add(customer);
                            int visitedTimes = Collections.frequency(allCustomer, customer);
                            customer.setVisitedTimes(visitedTimes);
                            rememberCustomerIndex += 1;
                            isEverythingOk = false;
                        }
                        else if(isParkingIn.equalsIgnoreCase("n")){
                            choose = 2;
                            isEverythingOk = false;
                        }
                        else{
                            System.out.println("Invalid Input. please try again.");
                            choose = -1;
                        }

                    }

                    showCustomerInteraction();
                    System.out.println();
                    int booking = 0;
                    isMismatch = true;
                    while(isMismatch){
                        try {
                            booking = in.nextInt();
                            isMismatch = false;
                        }
                        catch (InputMismatchException ime){
                            System.out.println("invalid number");
                            in.reset();
                            in.next();
                        }
                    }
                    switch (booking) {
                        case 1 -> { //(booking/reservation)
                            boolean isWorking = true;
                            while (isWorking) {
                                ticket = new Ticket();
                                ticketView = new TicketView();
                                ticketController = new TicketController(ticket, ticketView, customer, floors);
                                switch (choose) {
                                    case 1 -> { //entry booking
                                        String parkingSlotNumber = ticketController.getSlotNumber();


                                        if (parkingSlotNumber == null) {
                                            System.out.println("Sorry, there is no spot available for your vehicle");
                                            allCustomer.remove(rememberCustomerIndex);
                                            customers.remove(customer.getMobileNumber());
                                            rememberCustomerIndex--;
                                        } else {
                                            ticketController.setEntryTicket(parkingSlotNumber);
                                            updateEntrySlots(parkingSlotNumber,floors);
                                            tickets.put(parkingSlotNumber, ticket);
                                            int returnBack = 0;
                                            while (returnBack != -1) {
                                                ticketController.showTicket();
                                                System.out.println("Press -1 to go back: ");
                                                returnBack = in.nextInt();
                                                in.nextLine();
                                            }

                                        }
                                        isWorking = false;
                                    }
                                    case 2 -> {  //exit
                                        String customerCheck;
                                        Customer customer1 = null;
                                        boolean checkCustomer = true;
                                        while(checkCustomer){
                                            System.out.println("Enter your phone number: ");
                                            customerCheck = in.next();
                                            if(customers.containsKey(customerCheck)){
                                                customer1 = customers.get(customerCheck);
                                                checkCustomer = false;
                                            }
                                            else{
                                                System.out.println("wrong number enter the correct number");
                                            }
                                        }
                                        boolean isCorrect = true;
                                        while(isCorrect){
                                            System.out.println("Enter the correct slot number: ");
                                            String keySlot = in.next();
                                            if (tickets.containsKey(keySlot)){
                                                Ticket ticket1 = tickets.get(keySlot);
                                                ticket1.setExitTime();
                                                updateExitSlots(keySlot,floors);
                                                System.out.println("******** Transaction details ******** ");
                                                transactionView = new TransactionView();
                                                transactionController = new TransactionController(transactionView, customer1, ticket1);
                                                transactionController.showTicket();
                                                System.out.println("Thanks for parking! visit again !");
                                                isWorking = false;
                                                isCorrect = false;
                                            }
                                            else{
                                                System.out.println("Invalid slot number");
                                                isWorking = false;
                                            }
                                        }

                                    }
                                    default -> System.out.println("Invalid choice try again");
                                }
                            }
                        }
                        case 2 -> {  //reservation
                            ticket = new Ticket();
                            ticketView = new TicketView();
                            ticketController = new TicketController(ticket, ticketView, customer, floors, hoursInDay);
                            String parkingSlotNumber = ticketController.getSlotNumber();
                            if (parkingSlotNumber == null) {
                                System.out.println("Sorry, there is no spot available for your vehicle");
                            } else {
                                ticketController.setEntryTicket(parkingSlotNumber);
                                ticket.setExitTime();
                                updateEntrySlots(parkingSlotNumber,floors);
                                tickets.put(parkingSlotNumber, ticket);
                            }
                            System.out.println("******** Transaction details ******** ");
                            transactionView = new TransactionView();
                            transactionController = new TransactionController(transactionView, customer, ticket, hoursInDay);
                            transactionController.parkingBill();
                            transactionController.showTicket();
                            System.out.println("Thanks for parking...visit again !");
                        }

                        default -> System.out.println("Invalid data");
                    }
                }

                case 3 -> {                    // admin to close the system
                    boolean isAdmin = false;
                    while(!isAdmin){
                        System.out.println("Enter your name: ");
                        String checkName = in.next();
                        System.out.println("Enter password: ");
                        String checkPasswd = in.next();
                        if(checkName.equalsIgnoreCase(adminName) && checkPasswd.equals(adminPasswd)){
                            System.out.println("captcha to check you are not a robot :");
                            String captcha = generateCaptcha();
                            System.out.println("captcha : " + captcha);
                            String checkCaptcha = in.next();
                            if (captcha.equals(checkCaptcha)) {
                                isAdmin = true;
                            }
                        }
                    }
                    System.out.println("want to close the system ? (true/false)");
                    closeParkingSystem = in.nextBoolean();
                }
            }

        }
    }

    private static void updateEntrySlots(String parkingSlotNumber, HashMap<Integer, ParkingFloor> floors) {
        int floorNumber = Integer.parseInt(parkingSlotNumber.substring(2,4));
        switch (parkingSlotNumber.substring(0, 2)) {
            case "BI" -> floors.get(floorNumber).setBikeSlots(floors.get(floorNumber).getBikeSlots().length - 1);
            case "BU" -> floors.get(floorNumber).setBusSlots(floors.get(floorNumber).getBusSlots().length - 1);
            case "CA" -> floors.get(floorNumber).setCarSlots(floors.get(floorNumber).getCarSlots().length - 1);
        }
    }

    private static void updateExitSlots(String parkingSlotNumber, HashMap<Integer, ParkingFloor> floors) {
        int floorNumber = Integer.parseInt(parkingSlotNumber.substring(2,4));
        switch (parkingSlotNumber.substring(0, 2)) {
            case "BI" -> floors.get(floorNumber).setBikeSlots(floors.get(floorNumber).getBikeSlots().length + 1);
            case "BU" -> floors.get(floorNumber).setBusSlots(floors.get(floorNumber).getBusSlots().length + 1);
            case "CA" -> floors.get(floorNumber).setCarSlots(floors.get(floorNumber).getCarSlots().length + 1);
        }

    }

    private static void showSlots(HashMap<Integer, ParkingFloor> floors) {
        String leftAlignFormat = "| %-9s | %-9d | %-10d | %-9d|%n";

        System.out.format("+-----------+-----------+------------+----------+%n");
        System.out.format("| Floor no. | Car Slots | Bike Slots | Bus Slots|%n");
        System.out.format("+-----------+-----------+------------+----------+%n");
        floors.forEach(
                (floorNumber, floorDetail) ->
                        System.out.format(leftAlignFormat, floorNumber, floorDetail.getCarSlots().length,
                                floorDetail.getBikeSlots().length, floorDetail.getBusSlots().length));
        System.out.format("+-----------+-----------+------------+----------+%n");

    }

    public static void showInteraction(){
        System.out.println("+---------------------------------+");
        System.out.println("| 1. See available slots          |");
        System.out.println("| 2. Customer                     |");
        System.out.println("| 3. Admin                        |");
        System.out.println("+---------------------------------+");
    }

    private static void showCustomerInteraction() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
        System.out.println("*        1. (Entry/Exit) ticket                  *");
        System.out.println("*        2. Reserve a slot for whole day         *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
    }
    public static void showMenu(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
        System.out.println("*         1. entry ticket                       * ");
        System.out.println("*         2. exit  ticket                       * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
    }
    public static String generateCaptcha() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
