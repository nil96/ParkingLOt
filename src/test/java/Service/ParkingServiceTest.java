package Service;

import Actions.IParkingStrategy;
import Actions.NearestParkingSlot;
import enums.Command;
import model.Car;
import model.IVehicle;
import model.ParkingLot;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.inOrder;

public class ParkingServiceTest {

    ParkingService parkingService;
    NearestParkingSlot iParkingStrategy;

    @Before
    public void init() throws Exception {
        ParkingLot parkingLot = new ParkingLot(4);
        this.iParkingStrategy = new NearestParkingSlot(parkingLot);
        this.parkingService = new ParkingService(iParkingStrategy);
        this.parkingService.park("TA-01-HH-3141", "White");
    }

    @Test
    public void parkHappyCase() throws Exception {
        ParkingLot parkingLot = new ParkingLot(4);
        IParkingStrategy iParkingStrategy = new NearestParkingSlot(parkingLot);
        IParkingStrategy tmpIParkingStrategy = Mockito.spy(iParkingStrategy);
        ParkingService parkingService = new ParkingService(tmpIParkingStrategy);


        ParkingService tmpParkingService = Mockito.spy(parkingService);

        tmpParkingService.park("TA-02-HH-3141", "White");

        InOrder inOrder = inOrder(tmpParkingService);
        InOrder inOrder1 = inOrder(tmpIParkingStrategy);

        inOrder1.verify(tmpIParkingStrategy).add(any(IVehicle.class));

        inOrder.verify(tmpParkingService).isCarAlreadyPresent(anyString());
    }

    @Test(expected = Exception.class)
    public void parkNonHappyCase() throws Exception {
        parkingService.park("TA-02-HH-3141", "Black");
        InOrder inOrder = inOrder(parkingService);
        inOrder.verify(parkingService).isCarAlreadyPresent("TA-02-HH-3141");
    }

    @Test
    public void leave() {
    }

    @Test
    public void status() {
    }

    // REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR
    @Test
    public void getRegistrationWithColor() {
        ArrayList<String> details = parkingService.getDetails("White", Command.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
        assertEquals(details.get(0), "TA-01-HH-3141");
    }

    // SLOT_NUMBERS_FOR_CARS_WITH_COLOUR
    @Test
    public void getSlotWithColor() {
        ArrayList<String> details = parkingService.getDetails("White", Command.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
        assertEquals(details.get(0), "1");
    }
    // SLOT_NUMBER_FOR_REGISTRATION_NUMBER

    @Test
    public void getSlotWithRegistration() {
        ArrayList<String> details = parkingService.getDetails("TA-01-HH-3141", Command.SLOT_NUMBER_FOR_REGISTRATION_NUMBER);
        assertEquals(details.get(0), "1");
    }


    @Test
    public void isCarAlreadyPresent() {

    }
}