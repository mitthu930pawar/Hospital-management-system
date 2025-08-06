package hospitalmanagement;

import java.sql.*;
import java.util.Scanner;

public class hospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String name = "root";
    private static final String password = "mitthu123"; // Removed extra space

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, name, password);
            patient patient = new patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            while (true) {
                System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
                System.out.println("1. View Patients");
                System.out.println("2. View Doctors");
                System.out.println("3. Book Appointment");
                System.out.println("4. Add Patient");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.viewPatients(); // Corrected
                        break;
                    case 2:
                        doctor.viewDoctor();
                        break;
                    case 3:
                        bookAppointment(connection, scanner, patient, doctor);
                        break;
                    case 4:
                        patient.addPatient(); // add patient
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Enter valid number!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Connection connection, Scanner scanner, patient patient, Doctor doctor) {
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (yyyy-mm-dd): ");
        String appointmentDate = scanner.next();

        try {
            if (patient.checkpatient(patientId) && doctor.checkDoctor(doctorId)) {
                if (checkDoctorsAvailability(doctorId, appointmentDate, connection)) {
                    String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment booked successfully.");
                    } else {
                        System.out.println("Failed to book appointment.");
                    }
                } else {
                    System.out.println("Doctor is not available on that date.");
                }
            } else {
                System.out.println("Either the doctor or patient does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDoctorsAvailability(int doctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
