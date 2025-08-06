package hospitalmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class hospitalManagementSystem {
    private static final  String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String name = "root";
    private static final String password = " mitthu123";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

    }catch(ClassNotFoundException e){
          e.printStackTrace();
        }
        Scanner scanner =new Scanner(System.in);
        try{
         Connection connection = DriverManager.getConnection(url,name,password);
         patient patient = new patient(connection , scanner );
         Doctor doctor = new Doctor( connection);
         while(true){
             System.out.println(" HOSPITAL MANAGEMENT SYSTEM");
             System.out.println("1. view patients");
             System.out.println("2. view doctors");
             System.out.println("3. Book appointments");
             System.out.println("4. add patient");
             System.out.println("5. Exit");
             System.out.println("Enter your choice: ");
             int choice = scanner.nextInt();
             switch(choice){
                 case 1 :
                     //view patient
                     patient.addPatient();
                 case 2 :
                     // view doctors
                     doctor.viewDoctor();
                 case 3 :
                     // book appointment
                 case 4 :
                     // add patient
                     patient.viewPatients();
                 case 5 :
                     //exit
                     return;
                 default: System.out.println("Enter valid number!!!!");
             }
         }

     }catch(SQLException e){
         e.printStackTrace();
     }

        }
 public static void bookAppointment(Connection connection ,Scanner Scanner, patient patient,Doctor doctor ){

        System.out.println("Enter patient ID");
        int patientId = Scanner.nextInt();
     System.out.println("Enter doctor  ID");
       int doctorId = Scanner.nextInt();
       System.out.println("Enter doctor Id (yy-mm-dd)");
       String  appointmentDate = Scanner.next();
       if(patient.checkpatient(patientId)&& doctor.checkDoctor(doctorId)){

       }else{
          System.out.println("either doctor or patient not exist");
       }



 }

}
