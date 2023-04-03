package VehicleManager;

public class Car extends Vehicle {
    public int vehicleNoDoors;

    public String vehicleBodyStyle;

    public Car(String typeOfVehicle,
               String vehicleMake,
               String vehicleModel,
               int vehicleYear,
               String vehicleColor,
               String vehicleVin,
               String vehicleFuelType,
               int vehicleNoDoors,
               String vehicleBodyStyle) {
        super(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType);
        this.vehicleNoDoors = vehicleNoDoors;
        this.vehicleBodyStyle = vehicleBodyStyle;
    }

    public String toString() {
        return "Vehicle type: " + typeOfVehicle + " Make: " + vehicleMake + " Model: " + vehicleModel + " Year: " + "Color: " + vehicleColor + " VIN: " + vehicleVin + " Fuel: " + vehicleFuelType + " NoDoors: " + vehicleNoDoors + " Body style: " + vehicleBodyStyle;
    }
}