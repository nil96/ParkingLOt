package model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Car implements IVehicle {
       @NonNull
       String carRegistration;

       @NonNull
       String color;

}
