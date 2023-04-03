package VehicleManager;

public class Vehicle {
    public String typeOfVehicle;
    public String vehicleMake;
    public String vehicleModel;
    public int vehicleYear;
    public String vehicleColor;
    public String vehicleVin;
    public String vehicleFuelType;


    public Vehicle(String typeOfVehicle, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleColor, String vehicleVin, String vehicleFuelType) {
        this.typeOfVehicle = typeOfVehicle;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleColor = vehicleColor;
        this.vehicleVin = vehicleVin;
        this.vehicleFuelType = vehicleFuelType;
    }

    public String toString() {
        return "Vehicle type: " + typeOfVehicle + " Make: " + vehicleMake + " Model: " + vehicleModel + " Year: " + "Color: " + vehicleColor + " VIN: " + vehicleVin + " Fuel: " + vehicleFuelType;
    }
}




