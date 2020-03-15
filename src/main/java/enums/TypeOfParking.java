package enums;

public enum TypeOfParking {
    PREMIMUM("PREMIMUM"),
    ECONOMIC("ECONOMIC");

    private final String status;

    TypeOfParking(String status) {
        this.status = status;
    }

    public String toString() {
        return status;
    }

}
