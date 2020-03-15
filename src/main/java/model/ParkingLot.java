package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class ParkingLot {

       @Builder.Default
       int floor = 1;

       int parkingSize;

       ArrayList<ParkingSpot> parkingSpots;

       public ParkingLot(int parkingSize){
           this.parkingSize = parkingSize;
           this.parkingSpots = new ArrayList<>(parkingSize);
           for(int i=1;i<=parkingSize;i++){
               ParkingSpot parkingSpot = new ParkingSpot();
               System.out.print("Allocated slot number: " + i  +"\n");
               parkingSpot.setId(i);
               parkingSpots.add(parkingSpot);
           }
       }

}
