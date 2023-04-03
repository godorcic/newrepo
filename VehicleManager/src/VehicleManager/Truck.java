package VehicleManager;

public class Truck extends Vehicle {
    public String vehicleTowCap;
    public Truck(String typeOfVehicle, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleColor, String vehicleVin, String vehicleFuelType, String vehicleTowCap) {
        super(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType);
        this.vehicleTowCap = vehicleTowCap;
    }

    public String toString() {
        return "Vehicle type: " + typeOfVehicle + " Make: " + vehicleMake + " Model: " + vehicleModel + " Year: " +vehicleYear+ " Color: " + vehicleColor + " VIN: " + vehicleVin + " Fuel: " + vehicleFuelType + " Tow cap: " + vehicleTowCap;
    }


}
