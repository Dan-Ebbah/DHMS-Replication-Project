package client;

import model.User;
import server.ServerImpl;

import java.util.Scanner;


public class AdminClient {
    public static void main(String[] args) {
        try {

            User user = logIn();
            if (hasAccess(user)) {
                CommonServerImplInterface server = connectToRightServer(user);
                Scanner scanner = new Scanner(System.in);
                getOperation(scanner, server);
            } else {
                System.out.println("Wrong ID entered");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CommonServerImplInterface connectToRightServer(User user) {
        String hospitalName = user.getHospitalType().getHospitalName();

        switch (hospitalName.toLowerCase()) {
            case "montreal":
                return new MontrealServerImplService().getMontrealServerImplPort();
            case "sherbrooke":
                return new SherbrookeServerImplService().getSherbrookeServerImplPort();
            case "quebec":
                return new QuebecServerImplService().getQuebecServerImplPort();
            default:
                return null;
        }
    }

    private static void getOperation(Scanner scanner, CommonServerImplInterface server) throws Exception {

        while (true) {
            System.out.println("\nAdmin Operations:");
            System.out.println("\t1. Add Appointment");
            System.out.println("\t2. Remove Appointment");
            System.out.println("\t3. List Appointment Availability");
            System.out.println("\t4. Exit");
            System.out.print("Choose an operation: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            client.AppointmentType appointmentType;
            String appointmentID;
            switch (choice) {
                case 1:
                    appointmentID = getAppointmentID(scanner);
                    appointmentType = selectAppointmentType(scanner);

                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                        System.out.println(server.addAppointment(appointmentID, appointmentType, capacity));
                    break;
                case 2:
                    appointmentID = getAppointmentID(scanner);
                    appointmentType = selectAppointmentType(scanner);

                    System.out.println(server.removeAppointment(appointmentID, appointmentType));
                    break;
                case 3:
                    appointmentType = selectAppointmentType(scanner);
                    System.out.println(server.listAppointmentAvailability(appointmentType.value()));
                    break;
                case 4: // Exit
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static boolean hasAccess(User user) {
        return user != null && user.isAdmin();
    }

    private static String getAppointmentID(Scanner scanner) throws Exception {
        System.out.print("Enter Appointment ID: ");
        String appointmentIDAdd = scanner.nextLine();
        validateAppointmentID(appointmentIDAdd);
        return appointmentIDAdd;
    }

    static client.AppointmentType selectAppointmentType(Scanner scan) {
        System.out.println("Enter Appointment Type: ");
        System.out.println("\t1. Physician");
        System.out.println("\t2. Dental");
        System.out.println("\t3. Surgeon");

        System.out.print("Select a type: ");
        int choice = scan.nextInt();
        scan.nextLine();

        switch (choice) {
            case 1:
                return client.AppointmentType.PHYSICIAN;
            case 2:
                return client.AppointmentType.DENTAL;
            case 3:
                return AppointmentType.SURGEON;
        }
        return null;
    }

    private static void validateAppointmentID(String appointmentIDAdd) throws Exception {
        if (appointmentIDAdd.length() != 10) {
            System.out.println("INVALID APPOINTMENT ID");
            throw new Exception("INVALID APPOINTMENT ID");
        }
    }

    private static User logIn() {
        System.out.print("\nHello please enter your ID number: ");
        Scanner scanner = new Scanner(System.in);
        String clientID = scanner.nextLine().toUpperCase();
        User user = validateAndExtractIdInfo(clientID);
        return user;
    }

    static User validateAndExtractIdInfo(String clientID) {
        if( isValidId(clientID) ) {
            return extractInfo(clientID);

        }
        return null;
    }

    private static User extractInfo(String clientID) {

        return new User(clientID);
    }

    private static boolean isValidId(String clientID) {
        return clientID.length() == 8;
    }

}
