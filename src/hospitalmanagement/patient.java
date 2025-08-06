package hospitalmanagement;
import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class patient {
    private Connection connection;
    private Scanner scanner;

    public patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;

    }

    public void addPatient() {
        System.out.print("Enter your Name.");
        String Name = scanner.next();
        System.out.print("Enter your Age.");
        int Age = scanner.nextInt();
        System.out.println("Enter you Gender.");
        String Gender = scanner.next();
        try {
            String query = "INSERT INTO patient(name , age , gender ) VALUES(? , ?, ?)";
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, Name);
            preparedstatement.setInt(2, Age);
            preparedstatement.setString(3, Gender);
            int affectedRows = preparedstatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("patient  uploaded Successfully");
            } else {
                System.out.println(" patient did ot added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewPatients() {
        String query = "select * from patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("patients :");
            System.out.println("+---------+--------------------+----------+-----------+");
            System.out.println("|patient Id | Name             |age       | gender      |");
            System.out.println("+---------+--------------------+----------+-----------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%-12s / | %-20s | %-10s | %-15s| /\n", id , name , age, gender);
                System.out.println("+---------+--------------------+----------+-----------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkpatient(int id) {
        String query = " SELECT * From patients where id = ? ";
        try {
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setInt(1 ,id);
            ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
          return true;
        }
        else{
        return false;
        }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }
}