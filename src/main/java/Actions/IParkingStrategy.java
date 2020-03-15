package Actions;

import Exceptions.UnavailableSlot;
import lombok.Getter;
import model.IVehicle;
import model.ParkingLot;
import model.ParkingSpot;

import java.util.ArrayList;

public interface IParkingStrategy {
    public ParkingLot getParkingLot();

    public int add(IVehicle vehicle) throws UnavailableSlot;

    public ParkingSpot getSlot() throws UnavailableSlot;

    public int add(int slotNumber, IVehicle vehicle) throws UnavailableSlot;

    public void removeSlot(int slot) throws UnavailableSlot;

    public void removeSlot(IVehicle vehicle) throws UnavailableSlot;
}
