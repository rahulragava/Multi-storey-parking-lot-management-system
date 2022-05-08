package App;

import customer.*;
import parkingfloor.*;
import parkingticket.*;
import transaction.*;
import vehicle.*;

import java.util.*;

public class Main {
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
        int visitedTimes = 0;
        int hoursInDay = 24;
        boolean closeParkingSystem = false;
        while(!closeParkingSystem)
        {
            showInteraction();
            System.out.println("Enter the choice: (1/2/3/4)");
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
                case 1 -> {                                //showing the available slots in each floors
                    int goBack = 0;
                    while (goBack != -1) {
                        showSlots(floors);
                        System.out.println("Press -1 to go back");
                        goBack = in.nextInt();
                        in.nextLine();
                    }
                }
                case 2 -> {                                  //entering customer details. and book a slot
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
                            if(customers.containsKey(customer.getMobileNumber())){
                                visitedTimes++;
                                customer.setVisitedTimes(visitedTimes);
                            }
                            else{
                                visitedTimes = 1;
                                customer.setVisitedTimes(visitedTimes);
                            }
                            customers.put(customer.getMobileNumber(), customer);
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
                                            customers.remove(customer.getMobileNumber());
                                        }
                                        else {
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
                                                Random random = new Random();
                                                int generatingOtp = random.nextInt(1000,10000);
                                                showInMobile(generatingOtp);
                                                int typeOtp = 0;
                                                while(true){
                                                    isMismatch = true;
                                                    while(isMismatch){
                                                        try {
                                                            System.out.println("Enter the otp : ");
                                                            typeOtp = in.nextInt();
                                                            isMismatch = false;
                                                        }
                                                        catch (InputMismatchException ime){
                                                            System.out.println("invalid number");
                                                            in.reset();
                                                            in.next();
                                                        }
                                                    }
                                                    if(typeOtp == generatingOtp){
                                                        System.out.println("Welcome back " + customer1.getName());
                                                        break;
                                                    }
                                                    else{
                                                        System.out.println("Wrong otp");
                                                    }

                                                }



                                            }
                                            else{
                                                System.out.println("wrong number enter the correct number");
                                            }
                                        }
                                        boolean isCorrect = true;
                                        while(isCorrect){
                                            System.out.println("Enter the correct ticket number: ");
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

                case 3 -> {                    // admin to change parking price in the system
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
                    System.out.println("        ***** Welcome Admin *****");
                    loop:
                    while(true){
                        isMismatch = true;
                        int adminChoice = 0;
                        showAdminMenu();
                        while(isMismatch){
                            try {
                                System.out.println("Enter your choice: (1/2)");
                                adminChoice = in.nextInt();
                                isMismatch = false;
                            }
                            catch (InputMismatchException ime){
                                System.out.println("invalid number");
                                in.reset();
                                in.next();
                            }
                        }
                        switch (adminChoice){
                         case 1 ->{
                             System.out.println("select the vehicle you want to change the ticket fair, mr.admin");
                             isMismatch = true;
                             int internalChoice = 0;
                             showVehicleMenu();
                             while(isMismatch) {
                                 try {
                                     System.out.println("Enter your choice: (1/2/3)");
                                     internalChoice = in.nextInt();
                                     isMismatch = false;
                                 }
                                 catch(InputMismatchException ime){
                                     System.out.println("invalid number");
                                     in.reset();
                                     in.next();
                                 }
                             }
                             switch (internalChoice){
                                 case 1 -> { //admin changing bike fair
                                     isMismatch = true;
                                     while(isMismatch) {
                                         try {
                                             System.out.println("press 1 to change first hour rate of bike parking");
                                             System.out.println("press 2 to change remaining hours rate of bike parking");
                                             internalChoice = in.nextInt();
                                             isMismatch = false;
                                         } catch (InputMismatchException ime) {
                                             System.out.println("invalid number");
                                             in.reset();
                                             in.next();
                                         }
                                     }
                                     if(internalChoice == 1){
                                         System.out.println("current bike's first hour parking fair" + Bike.bikeFirstHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the bikes first hour rate should be: ");
                                                 Bike.bikeFirstHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("Bike's first hour parking price is changed to :" + Bike.bikeFirstHourParkingPrice);
                                     }
                                     if(internalChoice == 2){
                                         System.out.println("current bike's first hour parking fair" + Bike.bikeRemainingHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the bikes remaining hours rate should be: ");
                                                 Bike.bikeRemainingHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("Bike's remaining hours parking price is changed to :" + Bike.bikeRemainingHourParkingPrice);
                                     }
                                 }
                                 case 2 -> { // admin changing car fair
                                     isMismatch = true;
                                     while(isMismatch) {
                                         try {
                                             System.out.println("press 1 to change first hour rate of car parking");
                                             System.out.println("press 2 to change remaining hours rate of car parking");
                                             internalChoice = in.nextInt();
                                             isMismatch = false;
                                         } catch (InputMismatchException ime) {
                                             System.out.println("invalid number");
                                             in.reset();
                                             in.next();
                                         }
                                     }
                                     if(internalChoice == 1){
                                         System.out.println("current car's first hour parking fair" + Car.carFirstHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the car's first hour rate should be: ");
                                                 Car.carFirstHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("car's first hour parking price is changed to : " + Car.carFirstHourParkingPrice);
                                     }
                                     if(internalChoice == 2){
                                         System.out.println("current bike's first hour parking fair" + Bike.bikeRemainingHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the bikes remaining hours rate should be: ");
                                                 Bike.bikeRemainingHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("car's remaining hours parking price is changed to : " + Car.carRemainingParkingHourPrice);
                                     }
                                 }
                                 case 3 -> {      // admin changing bus fair
                                     isMismatch = true;
                                     while(isMismatch) {
                                         try {
                                             System.out.println("press 1 to change first hour rate of bus parking");
                                             System.out.println("press 2 to change remaining hours rate of bus parking");
                                             internalChoice = in.nextInt();
                                             isMismatch = false;
                                         } catch (InputMismatchException ime) {
                                             System.out.println("invalid number");
                                             in.reset();
                                             in.next();
                                         }
                                     }
                                     if(internalChoice == 1){
                                         System.out.println("current bus's first hour parking fair" + Bus.busFirstHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the bus's first hour rate should be: ");
                                                 Bus.busFirstHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("bus's first hour parking price is changed to : " + Bus.busFirstHourParkingPrice);
                                     }
                                     if(internalChoice == 2){
                                         System.out.println("current bus's first hour parking fair" + Bus.busFirstHourParkingPrice);
                                         isMismatch = true;
                                         while(isMismatch) {
                                             try {
                                                 System.out.println("Enter the amount the bus remaining hours rate should be: ");
                                                 Bus.busFirstHourParkingPrice = in.nextInt();
                                                 isMismatch = false;
                                             } catch (InputMismatchException ime) {
                                                 System.out.println("invalid number");
                                                 in.reset();
                                                 in.next();
                                             }
                                         }
                                         System.out.println("bus's remaining hours parking price is changed to : " + Bus.busRemainingHourParkingPrice);
                                     }
                                 }
                             }
                         }
//                         case 2 ->{ //to close the system
//
//                             try{
//                                 System.out.println("want to close the system ? (true/false)");
//                                 closeParkingSystem = in.nextBoolean();
//                                 break loop;
//                             }
//                             catch (InputMismatchException ime){
//                                 System.out.println("Invalid input");
//                                 in.reset();
//                                 in.next();
//                             }
//                         }
                            case 2 -> {
                                break loop;
                            }
                        default -> System.out.println("Invalid input");
                        }
                    }
                }
                case 4 -> closeParkingSystem = true;

                default -> System.out.println("Invalid input");
            }
        }
    }


    private static void showAdminMenu() {
        System.out.println("+---------------------------------+");
        System.out.println("| 1. change vehicle's hourly rate |");
        System.out.println("| 2. go back                      |");
        System.out.println("+---------------------------------+");
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
        System.out.println("| 4. exit                         |");
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
    private static void showVehicleMenu() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
        System.out.println("*         1. Change Bike fair                   * ");
        System.out.println("*         2. Change Car fair                    * ");
        System.out.println("*         3. Change Bus fair                    * ");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * ");
    }

    private static void showInMobile(int generatingOtp) {
        System.out.println("              mobile phone                    ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        *                       *             ");
        System.out.println("        *                       *             ");
        System.out.println("        *      OTP RECEIVED     *             ");
        System.out.printf( "        *          %d          *             ",generatingOtp);
        System.out.println();
        System.out.println("        *                       *             ");
        System.out.println("        *                       *             ");
        System.out.println("        *                       *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");
        System.out.println("        * * * * * * * * * * * * *             ");

    }
}

