package transaction;

public class TransactionView {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void generateTicket(String slotNumber, String vehicleNumber, String entryTime, String exitTime){
        System.out.println("+----------------------------------------------+");
        System.out.println("|               PARKING TICKET                 |");
        System.out.println("+----------------------------------------------+");
        System.out.println();
        System.out.printf("|  Slot number    :      %-10s            |",slotNumber);
        System.out.println();
        System.out.printf("|  Vehicle number :      %-10s            |",vehicleNumber);
        System.out.println();
        System.out.printf("|  Entry time     :      %-10s            |",entryTime);
        System.out.println();
        System.out.printf("|  Exit time      :      %-10s            |",exitTime);
        System.out.println();
        System.out.println("+----------------------------------------------+");

        int totalHour = Integer.parseInt(exitTime.substring(0,2)) - Integer.parseInt(entryTime.substring(0,2));
        if(entryTime.equals(exitTime)){
            totalHour = 24;
        }
        System.out.printf("| Total hours    :     %10s          |", totalHour);
        System.out.println();

    }



}
