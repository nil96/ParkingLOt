package enums;

public enum Command {
    CREATE_PARKING_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour"),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number");
    private final String status;

    Command(String status) {
        this.status = status;
    }

    public String toString() {
        return status.toString();
    }


    static public boolean isMember(String aName) {
        Command[] cmds = Command.values();
        for (Command cmd : cmds) {
            if (cmd.status.equals(aName)) {
                return true;
            }
        }
        return false;
    }

    static public Command fromString(String aName){
        Command[] cmds = Command.values();
        Command tmp = null;
        for (Command cmd : cmds) {
            if (cmd.status.equals(aName)) {
                tmp = cmd;
            }
        }
        return tmp;
    }
}
