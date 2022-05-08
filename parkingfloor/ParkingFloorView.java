package parkingfloor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingFloorView {
    int spaces;
    static int count = 1;
    public void interaction(){
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of spaces for "  + count +" floor : ");
        boolean isMismatch = true;
        boolean isOk = true;
        while(isMismatch){
            try {

                while(isOk){
                    spaces = in.nextInt();
                    if(spaces >= 5){
                        isOk = false;
                    }
                    else{
                        System.out.println("give at least 5 spaces");
                    }
                    isMismatch = false;
                }
            }
            catch (InputMismatchException ime){
                System.out.println("invalid number");
                in.reset();
                in.next();
            }
        }
        count++;
    }
}
