package Actions;

import Exceptions.UnavailableSlot;
import enums.ParkingStatus;
import lombok.Getter;
import lombok.Setter;
import model.IVehicle;
import model.ParkingLot;
import model.ParkingSpot;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class NearestParkingSlot implements IParkingStrategy {

    public ParkingLot parkingLot;
    ArrayList<ParkingSpot> parkingSpots;

    public NearestParkingSlot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingSpots = parkingLot.getParkingSpots();
    }

    @Override
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    @Override
    public int add(IVehicle vehicle) throws UnavailableSlot {
        ParkingSpot parkingSpot = this.getSlot();
        parkingSpot.setStartTime(new Date());
        parkingSpot.setParkingStatus(ParkingStatus.FILLED);
        parkingSpot.setVehicle(vehicle);
        return parkingSpot.getId();
    }

    public int add(int slotNumber, IVehicle vehicle) throws UnavailableSlot {
        if (slotNumber >= parkingLot.getParkingSize()) {
            throw new UnavailableSlot("Parking slot doesnt exsist");
        }
        if (parkingSpots.get(slotNumber).getParkingStatus() == ParkingStatus.FILLED) {
            throw new UnavailableSlot("Spot is already taken");
        }
//        parkingSpots.setStartTime(new Date());
        parkingSpots.get(slotNumber).setParkingStatus(ParkingStatus.FILLED);
        parkingSpots.get(slotNumber).setVehicle(vehicle);
        parkingSpots.get(slotNumber).setStartTime(new Date());
        return parkingSpots.get(slotNumber).getId();
    }

    @Override
    public ParkingSpot getSlot() throws UnavailableSlot {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkingStatus() == ParkingStatus.VACANT) {
                return parkingSpot;
            }
        }
        throw new UnavailableSlot("Sorry, parking lot is full");
    }

    @Override
    public void removeSlot(int slotNumber) throws UnavailableSlot {
        if (slotNumber >= parkingLot.getParkingSize()) {
            throw new UnavailableSlot("Parking slot doesnt exsist\n");
        }
        parkingSpots.get(slotNumber).setParkingStatus(ParkingStatus.VACANT);
        parkingSpots.get(slotNumber).setEndTime(new Date());
    }

    @Override
    public void removeSlot(IVehicle vehicle) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getVehicle() == vehicle) {
                parkingSpot.setParkingStatus(ParkingStatus.VACANT);
                parkingSpot.setEndTime(new Date());
            }
        }
    }
}
