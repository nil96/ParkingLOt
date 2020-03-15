package Actions;

import Exceptions.UnavailableSlot;
import enums.Command;
import enums.ParkingStatus;
import model.Car;
import model.IVehicle;
import model.ParkingLot;
import model.ParkingSpot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class NearestParkingSlotTest {

    NearestParkingSlot nearestParkingSlot;
    ParkingLot parkingLot;

    @Before
    public void init() {
           ParkingLot parkingLot = new ParkingLot(4);
           nearestParkingSlot = new NearestParkingSlot(parkingLot);

    }

    @Test
    public void getParkingLot() {

    }

    @Test
    public void add() throws UnavailableSlot {
           Car car = new Car("number","color");
           int id = nearestParkingSlot.add(car);
           ParkingSpot parkingSpot = nearestParkingSlot.getParkingSpots().get(id-1);
           assertEquals(parkingSpot.getVehicle(),car);
    }

    @Test
    public void add1() {
    }

    @Test
    public void getSlot() throws  Exception {
        ParkingSpot parkingSpot = nearestParkingSlot.getSlot();
        ParkingSpot parkingSpot1 = nearestParkingSlot.getParkingSpots().get(0);
        assertEquals(parkingSpot,parkingSpot1);

    }

    @Test
    public void removeSlot() throws  Exception{
        nearestParkingSlot.removeSlot(0);
        assertEquals(nearestParkingSlot.getParkingSpots().get(0).getParkingStatus(), ParkingStatus.VACANT);
    }

    @Test
    public void removeSlot1() {
    }

    @Test
    public void getParkingSpots() {
    }

    @Test
    public void setParkingLot() {
    }

    @Test
    public void setParkingSpots() {
    }
}