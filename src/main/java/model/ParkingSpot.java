package model;

import enums.ParkingStatus;
import enums.TypeOfParking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;


@Getter
@Setter
public class ParkingLot {
       long id;

       IVehicle vehicle;

       ParkingStatus parkingStatus;

       Date startTime;

       Data endTime;

       @Builder.Default
       TypeOfParking typeOfParking = TypeOfParking.ECONOMIC;

       @Builder.Default
       float rate = 10.0f;
}
