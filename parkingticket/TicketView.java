package parkingticket;

public class TicketView {
    public final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void generateTicket(String slotNumber, String vehicleNumber, String entryTime, String exitTime){
        System.out.println("+----------------------------------------------+");
        System.out.println("|               PARKING TICKET                 |");
        System.out.println("+----------------------------------------------+");
        System.out.println("|                                              |");
        System.out.printf("|  Slot number    :      %-10s            |",slotNumber);
        System.out.println();
        System.out.printf("|  Vehicle number :      %-10s            |",vehicleNumber);
        System.out.println();
        System.out.printf("|  Entry time     :      %-10s            |",entryTime);
        System.out.println();
        System.out.printf("|  Exit time      :      %-10s            |",exitTime);
        System.out.println();
        System.out.println("+----------------------------------------------+");
        if(exitTime.equals("--:--:--")){
            System.out.println();
            System.out.println("+------------------------------------------------------+");
            System.out.println();
            System.out.println(ANSI_YELLOW + "|        PLEASE REMEMBER (SLOT NUMBER/VEHICLE NUMBER)   |" + ANSI_RESET);
            System.out.println("+------------------------------------------------------+");
        }
        else{
            int totalHour = Integer.parseInt(exitTime.substring(0,2)) - Integer.parseInt(entryTime.substring(0,2));
            if(entryTime.equals(exitTime)){
                totalHour = 24;
            }
            System.out.println("+------------------------------------------------------+");
            System.out.printf("|       Total hours    :     %10s                      |", totalHour);
            System.out.println();
            System.out.println("+------------------------------------------------------+");
        }
    }
}
