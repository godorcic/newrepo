package VehicleManager;

public class EmptyInput {
    public String inputXx;
    public boolean empty;

    public EmptyInput(String vehicleMake) {
        empty = false;
        if (inputXx == "")   {
            empty = true;
        }
    }
public boolean empty () {
        return empty;
}
}
