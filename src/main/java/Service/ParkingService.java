package Service;

import Actions.IParkingStrategy;
import Actions.NearestParkingSlot;
import Exceptions.UnavailableSlot;
import Exceptions.VehicleAlreadyPresent;
import enums.Command;
import enums.ParkingStatus;
import model.Car;
import model.IVehicle;
import model.ParkingLot;
import model.ParkingSpot;

import javax.management.InvalidAttributeValueException;
import java.util.ArrayList;

public class ParkingService {
    IParkingStrategy iParkingStrategy;
    ArrayList<ParkingSpot> parkingSpots;

    public ParkingService(IParkingStrategy parkingStrategy) {
        this.iParkingStrategy = parkingStrategy;
        this.parkingSpots = iParkingStrategy.getParkingLot().getParkingSpots();
    }

    public void park(String vehicleNumber, String color) throws UnavailableSlot, VehicleAlreadyPresent {
        if (isCarAlreadyPresent(vehicleNumber) == false) {
            IVehicle vehicle = new Car(vehicleNumber, color);
            int id = iParkingStrategy.add(vehicle);
            System.out.print("Allocated slot number: " + id + "\n");
        }
    }

    public void leave(int slot) throws UnavailableSlot {
        iParkingStrategy.removeSlot(slot - 1);
        System.out.print("Slot number " + slot + " is free\n");
    }

    public void status() {
        System.out.println("Slot No.   Registration No      Colour");
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkingStatus() == ParkingStatus.FILLED) {
                Car car = (Car) parkingSpot.getVehicle();
                System.out.println(parkingSpot.getId() + "          " + car.getCarRegistration() + "             " + car.getColor());
            }
        }
    }

    public ArrayList<String> getDetails(String value, Command cmd) {
        ArrayList<String> details = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            Car car = (Car) parkingSpot.getVehicle();
            if (parkingSpot.getParkingStatus() == ParkingStatus.FILLED) {
                if (cmd == Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR && car.getColor().equalsIgnoreCase(value)) {
                    details.add(car.getCarRegistration());
                } else if (cmd == Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR && car.getColor().equalsIgnoreCase(value)) {
                    details.add(Integer.toString(parkingSpot.getId()));
                } else if (cmd == Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER && car.getCarRegistration().equalsIgnoreCase(value)) {
                    details.add(Integer.toString(parkingSpot.getId()));
                }
            }
        }
        if (details.size() == 0) {
            System.out.print("Not found\n");
            details.add("Not found");
            return details;
        }

        for (int detail = 0; detail < details.size() - 1; detail++) {
            System.out.print(details.get(detail) + ", ");
        }
        if ((details.size() - 1) >= 0)
            System.out.print(details.get(details.size() - 1));
        return details;
    }

    public boolean isCarAlreadyPresent(String vehicleNumber) throws VehicleAlreadyPresent {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkingStatus() == ParkingStatus.FILLED) {
                Car car = (Car) parkingSpot.getVehicle();
                if (car.getCarRegistration() == vehicleNumber) {
                    throw new VehicleAlreadyPresent("Vehicle is already present");
                }
            }
        }
        return false;
    }
}
