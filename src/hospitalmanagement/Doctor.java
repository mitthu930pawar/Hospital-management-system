package hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Doctor {
    private Connection connection;


    public Doctor(Connection connection) {

        this.connection = connection;
    }

    public void viewDoctor() {
        String Query = " SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet resultset = preparedStatement.executeQuery();
            System.out.println("Doctors :");
            System.out.println("+---------+--------------------+---------------------+");
            System.out.println("|Doctors Id | Name             |specialization    |");
            System.out.println("+---------+--------------------+---------------------+");
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                String specialization = resultset.getString("specialization");
                System.out.printf("|%-12s | %-20s |%-20s | \n", id, name, specialization);
                System.out.println("+---------+--------------------+---------------------+");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean checkDoctor(int Id) {
        String query = "select * from doctors where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Id);
            ResultSet resultset =  preparedStatement.executeQuery( query);
            if(resultset.next()){
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
