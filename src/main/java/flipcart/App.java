package flipcart;

import Actions.IParkingStrategy;
import Actions.NearestParkingSlot;
import Exceptions.UnavailableSlot;
import Service.ParkingService;
import enums.Command;
import model.ParkingLot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.compare;
import static java.lang.Integer.parseInt;

/**
 * Hello world!
 */


public class App {

    public static ParkingService getParkingService(int n) {
        ParkingLot parkingLot = new ParkingLot(n);
        IParkingStrategy iParkingStrategy = new NearestParkingSlot(parkingLot);
        ParkingService parkingService = new ParkingService(iParkingStrategy);
        return parkingService;
    }

    public static void main(String[] args) {
        System.out.println("********************************************************");
        System.out.println("                Parking App is up\n");
        System.out.println("********************************************************");
        ParkingService parkingService = null;
        String query = "";
        System.out.println("Please Enter 'exit' to end Execution");
        System.out.println("Input:");
        while (true) {
            try {
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
                String input = bufferReader.readLine().trim();
                String[] arr = input.split(" ");
                if (arr[0].equalsIgnoreCase("exit")) {
                    break;
                }
                Command cmd;
                if (Command.isMember(arr[0]) == true) {
                    cmd = Command.fromString(arr[0]);
                } else {
                    System.out.println("Please enter valid command");
                    continue;
                }
                if (cmd == Command.CREATE_PARKING_LOT) {
                    int parkingSize = Integer.parseInt(arr[1]);
                    parkingService = getParkingService(parkingSize);
                    continue;
                }
                if (parkingService == null) {
                    System.out.println("Please create parking lot before parking using command :- create_parking_lot");
                    continue;
                }
                if (cmd == Command.STATUS) {
                    parkingService.status();
                    continue;
                }
                if (cmd == Command.LEAVE) {
                    int slot = Integer.parseInt(arr[1]);
                    parkingService.leave(slot);
                    continue;
                }
                if (cmd == Command.PARK) {
                    parkingService.park(arr[1], arr[2]);
                    continue;
                }
                parkingService.getDetails(arr[1], cmd);

            } catch (UnavailableSlot e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
