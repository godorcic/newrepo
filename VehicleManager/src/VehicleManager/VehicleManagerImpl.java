package VehicleManager;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class VehicleManagerImpl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        int intinput;

/** Program for vehicle management with options
 *   1. Add a vehicle to the fleet
 *   2. Search for vehicles in the fleet
 *   3. Print all vehicles in the fleet
 *   4. Delete a vehicle from the fleet
 *   5. Quit
 *   Choose option 1-5, tested string input if it is valid integer 1-5 as variable intinput
 *   If not one of options 1-5 restart of menu input
 *
 */
        boolean b = true;
  /*      List<String> listtypeOfVehicle = new ArrayList<>();
        List<String> listMake = new ArrayList<>();
        List<String> listModel = new ArrayList<>();
        List<Integer> listYear = new ArrayList<>();
        List<String> listColor = new ArrayList<>();
*/
        List<String> listVin = new ArrayList<>();
  /*      List<String> listFuelType = new ArrayList<>();
        List<Integer> listNoDoors = new ArrayList<>();
        List<String> listBodyStyle = new ArrayList<>();
        List<String> listTowCap = new ArrayList<>();
*/
        List<Vehicle> listVehicle = new LinkedList<>();
        List<Car> listCar = new LinkedList<>();
        List<Truck> listTruck = new LinkedList<>();
        String typeOfVehicle = "";
        String vehicleMake = "";
        String vehicleModel = "";
        int vehicleYear = 0;
        String vehicleColor = "";
        String vehicleVin = "";
        String vehicleFuelType="";
        int vehicleNoDoors = 0;
        String vehicleBodyStyle = "";
        String vehicleTowCap = "";
        boolean vinNoDuplicate = false;
        boolean addedvehicle = false;
        String listVINx;
        int i;
        int j;
        int k = 0;

        /**   Logiranje
         */
        Logger LOGGER = Logger.getLogger(VehicleManagerImpl.class.getName());
        try {
            FileHandler fileHandler = new FileHandler("log.txt");
            ((Logger) LOGGER).addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            ((Logger) LOGGER).warning("Log datoteka se nije uspijela kreirati: " + e.getMessage());
        }

        do {
            System.out.println("Vehicle Fleet Management System ");
            System.out.println("_______________________________ ");
            System.out.println("1. Add a vehicle to the fleet ");
            System.out.println("2. Search for vehicles in the fleet by make, model or VIN");
            System.out.println("3. Print all vehicles in the fleet ");
            System.out.println("4. Delete a vehicle from the fleet ");
            System.out.println("5. Quit ");
            vinNoDuplicate = false;

            System.out.println("Enter command (1-5) ");
            String input = scanner.nextLine();
            try {
                intinput = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ((Logger) LOGGER).warning("Invalid input. Please enter valid integer (1-5). ");
                System.out.println("Invalid input. Please enter valid integer (1-5). ");
                continue; // Vrati se na početak
            }

            if (intinput < 1) {
                System.out.println("Invalid input. Please enter valid integer (1-5). ");
                continue; // Vrati se na početak
            }
            if (intinput > 5) {
                System.out.println("Invalid input. Please enter valid integer (1-5). ");
                continue; // Vrati se na početak
            }

            /** ____________________________________________________________________________
             *      "1. Add a vehicle to the fleet " intinput == 1
             *         String typeOfVehicle = "car" or "truck"
             *         String vehicleMake = "";
             *         String vehicleModel = "";
             *         int vehicleYear = 0;
             *         String vehicleColor = "";
             *         String vehicleVin = "";
             *         int vehicleNoDoors = 0;
             *         String vehicleBodyStyle = "";
             *         String vehicleTowCap
             *      ________________________________________________________________________
             */
            if (intinput == 1) {
                do {
                    System.out.println("What type of vehicle would you like to add? ");
                    System.out.println("Enter 'car' or 'truck': ");
                    typeOfVehicle = scanner.nextLine();
                    if ((typeOfVehicle.equals("car")) || (typeOfVehicle.equals("truck"))) {
                        break;
                    } else {
                        System.out.println("Please choose option ");
                        continue;
                    }
                } while (true);
                do {

                    System.out.println("Enter make: ");
                    vehicleMake = scanner.nextLine();
                } while (vehicleMake=="");
                do {
                System.out.println("Enter model: ");
                vehicleModel = scanner.nextLine();
                } while (vehicleModel=="");

                /**  Vehicle year has to be integer in range 1900 - current year
                 *   int currentYear = 1900+ java current year which is calculated surplus from 1900
                 *   try-catch for correct value for year
                 */

                Date d=new Date();
                int year=d.getYear();
                int currentYear = 1900+year;
                do {
                    System.out.println("Enter year of production: (4 digits) ");
                    String input4 = scanner.nextLine();
                    try {
                        vehicleYear = Integer.parseInt(input4);
                    } catch (NumberFormatException e) {
                        ((Logger) LOGGER).warning("Invalid input. Please enter valid year as integer 4 digits (1900 - current year). ");
                        System.out.println("Invalid input. Please enter valid year as integer 4 digits (1900 - current year). ");
                        continue; // Vrati se na početak
                    }
                    if ((vehicleYear < 1900) || (vehicleYear > currentYear)) {
                        ((Logger) LOGGER).warning("Invalid input. Please enter valid year as integer 4 digits (1900 - current year). ");
                        System.out.println("Invalid input. Please enter valid year as integer 4 digits (1900 - current year). ");
                        continue; // Vrati se na početak
                    }
                    break;
                } while (true);
                do {
                System.out.println("Enter color: ");
                vehicleColor = scanner.nextLine();
                } while (vehicleColor=="");
                do {
                System.out.println("Enter VIN: ");
                vehicleVin = scanner.nextLine();
                } while (vehicleVin=="");
                j = 1;
                System.out.println();

                if (listVin.isEmpty()) {
                    listVINx = "";
                    j = 0;
                }
                if (listVin.size() >= 1) {
                    j = listVin.size();
                    vinNoDuplicate = false;
                    for (i = 0; i < j; i++) {

                        listVINx = listVin.get(i);
                        //System.out.println();
                        try {
                            if (listVINx.equalsIgnoreCase(vehicleVin)) {
                                vinNoDuplicate = true;
                                throw new DuplicateVehicleException("Duplicate VIN number. ");
                            }
                        } catch (DuplicateVehicleException e) {
                            ((Logger) LOGGER).severe("Error: " + e.getMessage());
                            System.out.println("Error: " + e.getMessage());
                        }
                        if (vinNoDuplicate) {
                            break;
                        }
                    }

                }
                if (!vinNoDuplicate) {
                    listVin.add(vehicleVin);

                    do {
                    System.out.println("Enter fueltype: ");
                    vehicleFuelType = scanner.nextLine();
                    } while (vehicleFuelType=="");

                    if (typeOfVehicle.equals("car")) {
                        do {
                            System.out.println("Enter number of doors: ");
                            String input5 = scanner.nextLine();
                            try {
                                vehicleNoDoors = Integer.parseInt(input5);
                            } catch (NumberFormatException e) {
                                ((Logger) LOGGER).warning("Invalid input. Please enter valid integer for number of doors. ");
                                System.out.println("Invalid input. Please enter valid integer for number of doors. ");
                                continue; // Vrati se na početak
                            }
                            if ((vehicleNoDoors < 0) || (vehicleNoDoors > 99)) {
                                ((Logger) LOGGER).warning("Invalid input. Please enter valid integer for number of doors. ");
                                System.out.println("Invalid input. Please enter valid integer for number of doors. ");
                                continue; // Vrati se na početak
                            }
                            break;
                        } while (true);
                        do {
                           System.out.println("Enter body style: ");
                           vehicleBodyStyle = scanner.nextLine();
                            } while (vehicleBodyStyle=="");
                        /**
                         *  Added vehicles to the class List<Vehicle>
                         */
                        Vehicle vehicle = new Car(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType, vehicleNoDoors, vehicleBodyStyle);
                        //Car car = new Car(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType, vehicleNoDoors, vehicleBodyStyle);

                        if (vinNoDuplicate == false) {
                            listVehicle.add(vehicle);
                            k = listVehicle.size() - 1;
                            Vehicle index = listVehicle.get(k);
                            System.out.println(index);
                            System.out.println("Vehicle car added successfully ");
                            addedvehicle = true;
                            System.out.println();
                        }
                    }
                    if (typeOfVehicle.equals("truck")) {
                        do {
                            System.out.println("Enter towing capacity: ");
                            vehicleTowCap = scanner.nextLine();
                            } while (vehicleTowCap =="");
                            Vehicle vehicle = new Truck(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType, vehicleTowCap);
                        //Truck truck = new Truck(typeOfVehicle, vehicleMake, vehicleModel, vehicleYear, vehicleColor, vehicleVin, vehicleFuelType, vehicleTowCap);
                        if (vinNoDuplicate == false) {
                            listVehicle.add(vehicle);
                            k = listVehicle.size() - 1;
                            Vehicle index1 = listVehicle.get(k);
                            System.out.println(index1);
                            System.out.println("Vehicle added successfully ");
                            addedvehicle = true;
                            System.out.println();
                        }

                    }

                }
            }

            /** ____________________________________________________________________________
             * "2. Search for vehicles in the fleet by make, model or VIN" intinput == 2
             *      "1. Search by make "    intinputS == 1
             *      "2. Search by model "   intinputS == 2
             *      "3. Search by VIN "     intinputS == 3
             *      ________________________________________________________________________
             */
            if (intinput == 2) {
                int intinputS;
                String vehicleMakeSearch;
                String listMakex;
                int intFoundMake;
                System.out.println("How would you like to search for vehicles: ");
                System.out.println("___________________________________________ ");
                System.out.println("1. Search by make ");
                System.out.println("2. Search by model ");
                System.out.println("3. Search by VIN ");
                do {
                    System.out.println("Enter command (1-3) ");
                    String inputSearch = scanner.nextLine();
                    try {
                        intinputS = Integer.parseInt(inputSearch);
                    } catch (NumberFormatException e) {
                        ((Logger) LOGGER).warning("Invalid input. Please enter valid integer (1-3). ");
                        System.out.println("Invalid input. Please enter valid integer (1-3). ");
                        continue; // Vrati se na početak
                    }

                    if ((intinputS < 1) || (intinputS > 3)) {
                        ((Logger) LOGGER).warning("Invalid input. Please enter valid integer (1-3). ");
                        System.out.println("Invalid input. Please enter valid integer (1-3). ");
                        continue; // Vrati se na početak
                    }

                    /** print vehicles by searched Make
                     *   "1. Search by make"
                     *   String vehicleMakeSearch as input with ignored Case (x or X)
                     *   boolean makeSearch = false by default
                     */
                    if (intinputS == 1) {
                        System.out.println("Enter make: ");
                        vehicleMakeSearch = scanner.nextLine();
                        k = listVehicle.size();
                        boolean makeSearch = false;
                        for (i = 0; i < k; i++) {
                            Vehicle index2 = listVehicle.get(i);
                            String make = index2.vehicleMake;
                            if (make.equalsIgnoreCase(vehicleMakeSearch)) {
                                System.out.println(index2);
                                makeSearch = true;
                            }
                        }
                        if (!makeSearch) {
                            System.out.println("No Vehicle of make: " + vehicleMakeSearch);
                        }
                    }
                    /** print vehicles by searched Model
                     * 2. Search by model
                     * String vehicleModelSearch as input with ignored Case (x or X)
                     * boolean modelSearch = false by default
                     */
                    if (intinputS == 2) {
                        System.out.println("Enter model: ");
                        String vehicleModelSearch = scanner.nextLine();
                        k = listVehicle.size();
                        boolean modelSearch = false;
                        for (i = 0; i < k; i++) {
                            Vehicle index3 = listVehicle.get(i);
                            String model = index3.vehicleModel;
                            if (model.equalsIgnoreCase(vehicleModelSearch)) {
                                System.out.println(index3);
                                modelSearch = true;

                            }
                        }
                        if (!modelSearch) {
                            System.out.println("No Vehicle of model: " + vehicleModelSearch);
                        }
                    }
                    /**  print vehicles by searched VIN
                     *   "3. Search by VIN"
                     *   String vehicleVinSearch as input  with ignored Case (x or X)
                     *   boolean vinSearch = false by default
                     */
                    if (intinputS == 3) {
                        System.out.println("Enter VIN: ");
                        String vehicleVinSearch = scanner.nextLine();
                        k = listVehicle.size();
                        boolean vinSearch = false;
                        for (i = 0; i < k; i++) {
                            Vehicle index6 = listVehicle.get(i);
                            String vinNo = index6.vehicleVin;
                            if (vinNo.equalsIgnoreCase(vehicleVinSearch)) {
                                System.out.println(index6);
                                vinSearch = true;
                                break;
                            }

                        }
                        /**  throw NoSuchVehicleException in case that vehicle does not exist in List of vehicles
                         *   !vinSearch = false
                         */
                        if (!vinSearch) {
                            try {
                                throw new NoSuchVehicleException("Such vehicle does not exist in List of vehicles. ");
                                //System.out.println("No Vehicle with that VIN: " + vehicleVinSearch);
                            } catch (NoSuchVehicleException e) {
                                ((Logger) LOGGER).severe("Error: " + e.getMessage());
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    }

                } while (!true);
                //break;
            }


            /**  "3. Print all vehicles in the fleet "
             *    in List of vehicles
             */
            if (intinput == 3) {
                System.out.println("List of vehicles ");
                k = listVehicle.size();
                for (i = 0; i < k; i++) {
                    Vehicle indexn = listVehicle.get(i);
                    System.out.println(indexn);
                }

            }
            /**   "4. Delete a vehicle from the fleet "
             *     String vehicleVinDelete as input with ignored Case (x or X)
             *     boolean vinDelete = false by default
             */

            if (intinput == 4) {
                System.out.println("Enter vehicle VIN to be deleted from list: ");
                String vehicleVinDelete = scanner.nextLine();
                k = listVehicle.size();
                boolean vinDelete = false;
                for (i = 0; i < k; i++) {
                    Vehicle index6 = listVehicle.get(i);
                    String vinNo = index6.vehicleVin;
                    if (vinNo.equalsIgnoreCase(vehicleVinDelete)) {
                        listVehicle.remove(i);
                        vinDelete = true;
                        System.out.println("Vehicle VIN no: " + vehicleVinDelete + " removed successfully from list of vehicles");
                        break;
                    }
                }
                /**   throw NoSuchVehicleException in case that vehicle does not exist in List of vehicles
                 *   !vinDelete = false
                 */
                if (!vinDelete) {
                    //System.out.println("No Vehicle with VIN " + vehicleVinDelete + "in List of vehicles.");
                    try {
                        throw new NoSuchVehicleException("Such vehicle does not exist in List of vehicles. ");
                        //System.out.println("No Vehicle with that VIN: " + vehicleVinSearch);
                    } catch (NoSuchVehicleException e) {
                        System.out.println("Error: " + e.getMessage());
                        ((Logger) LOGGER).severe("Error: " + e.getMessage());
                    }
                }
            }


            /**   "5. Quit "
             *
             */

            if (intinput == 5) {
                System.out.println("Programe End.");
                ((Logger) LOGGER).info("Programe End.");
                break;
            }
        } while (true);

    }
}



