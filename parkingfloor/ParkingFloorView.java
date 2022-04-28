package parkingfloor;

import java.util.Scanner;

public class ParkingFloorView {
    int spaces;
    static int count = 1;
    public void interaction(){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of spaces for "  + count +" floor : ");
        spaces = in.nextInt();
        count++;
    }
}
