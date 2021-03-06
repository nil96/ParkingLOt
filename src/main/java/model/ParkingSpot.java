package model;

import enums.ParkingStatus;
import enums.TypeOfParking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class ParkingSpot {
       int id;

       IVehicle vehicle;

       @Builder.Default
       ParkingStatus parkingStatus = ParkingStatus.VACANT;

       Date startTime;

       Date endTime;

       @Builder.Default
       TypeOfParking typeOfParking = TypeOfParking.ECONOMIC;

       @Builder.Default
       float rate = 10.0f;
}
