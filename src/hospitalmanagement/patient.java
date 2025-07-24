package hospitalmanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class patient {
    private Connection connection;
    private Scanner scanner;
 public patient( Connection connection , Scanner scanner){
     this.connection = connection;
     this.scanner = scanner;

 }
public void  addPatient(){
     System .out.print("Enter your Name.");
     String Name = scanner.next();
     System.out.print( "Enter your Age.");
     int Age = scanner.nextInt();
     System.out.println("Enter you Gender.");
     String Gender = scanner.next();
     try{
        String query = "INSERT INTO patient(name , age , gender ) VALUES(? , ?, ?)";
         PreparedStatement preparedstatement = connection.prepareStatement(query);
         preparedstatement.setString(1,Name);
         preparedstatement.setInt(2, Age);
         preparedstatement.setString(3,Gender);
         int affectedRows = preparedstatement.executeUpdate();
         if(affectedRows > 0){
         System.out.println("patient  uploaded Successfully");
         }
         else { System.out.println(" patient did ot added");
         }
 }
     catch(Exception e){
        e.printStackTrace();
     }
public void removePatient()
}
}