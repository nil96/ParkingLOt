package enums;

public enum ParkingStatus {
    MAINTENANCE("MAINTENANCE"),
    FILLED("FILLED"),
    VACANT("VACANT");

    private final String status;

    ParkingStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }
}
