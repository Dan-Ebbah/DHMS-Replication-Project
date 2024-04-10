package client;

import client.AppointmentType;
import model.User;

import java.util.Scanner;

public class PatientClient extends AdminClient {
    private static String patientID;
    public static void main (String [] args) {
        try {

            User user = logIn();

            if (hasAccess(user)) {
                CommonServerImplInterface commonServerImplInterface = connectToRightServer(user);
                Scanner scanner = new Scanner(System.in);
                getOperation(scanner, commonServerImplInterface);
            } else {
                System.out.println("Wrong ID entered");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean hasAccess(User user) {
        return user != null && !user.isAdmin();
    }


    private static void getOperation(Scanner scanner, CommonServerImplInterface serverImplInterface) throws Exception {
        while (true) {
            System.out.println("\nPatient Operations:");
            System.out.println("\t1. Book Appointment");
            System.out.println("\t2. Get Appointment Schedule");
            System.out.println("\t3. Cancel Appointment");
            System.out.println("\t4. Swap Appointment");
            System.out.println("\t5. Exit");
            System.out.print("Choose an operation: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            AppointmentType appointmentType;
            String appointmentID;
            switch (choice) {
                case 1:
                    appointmentID = getAppointmentID(scanner);
                    appointmentType = selectAppointmentType(scanner);
                    System.out.println(serverImplInterface.bookAppointment(patientID, appointmentType, appointmentID));
                    break;
                case 2:
                    System.out.println(serverImplInterface.getAppointmentSchedule(patientID));
                    break;
                case 3:
                    appointmentID = getAppointmentID(scanner);
                    appointmentType = selectAppointmentType(scanner);
                    System.out.println(serverImplInterface.cancelAppointment(patientID, appointmentID));
                    break;
                case 4:
                    System.out.println("**** Enter Old Appointment Info ******** ");
                    String oldAppointment = getAppointmentID(scanner);
                    AppointmentType oldAppointmentType = selectAppointmentType(scanner);

                    System.out.println("**** Enter New Appointment Info ******** ");
                    appointmentID = getAppointmentID(scanner);
                    appointmentType = selectAppointmentType(scanner);
                    System.out.println(serverImplInterface.swapAppointment(patientID, oldAppointmentType.value(), oldAppointment, appointmentType.value(), appointmentID));
                    break;
                case 5: // Exit
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String getAppointmentID(Scanner scanner) throws Exception {
        System.out.print("Enter Appointment ID: ");
        String appointmentIDAdd = scanner.nextLine();
        validateAppointmentID(appointmentIDAdd);
        return appointmentIDAdd;
    }

    private static void validateAppointmentID(String appointmentIDAdd) throws Exception {
        if (appointmentIDAdd.length() != 10) {
            System.out.println("INVALID APPOINTMENT ID");
        }
    }


    private static User logIn() {
        System.out.print("\nHello and Welcome please enter your ID number: ");
        Scanner scanner = new Scanner(System.in);
        String clientID = scanner.nextLine().toUpperCase();
        User user = validateAndExtractIdInfo(clientID);
        patientID = user.getUserId();
        return user;
    }
}
