package com.niveus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Assignment2 
{
    public static void main( String[] args )
    {
    	Connection connection = null;

        try {
            connection = DriverManager.getConnection(CRUDOperations.DB_URL, CRUDOperations.DB_USER, CRUDOperations.DB_PASSWORD);

//            // Create
           CRUDOperations.insertData(connection, 7, "dhanya", 21);

           // Update
          CRUDOperations.updateData(connection, 7, "dhanya shetty", 26);

//            // Delete
            CRUDOperations.deleteData(connection, 3);
           
           
           // Read
           ResultSet resultSet = CRUDOperations.retrieveData(connection);
           while (resultSet.next()) {
               System.out.println("ID: " + resultSet.getInt("id") +
                       ", Name: " + resultSet.getString("name") +
                       ", Age: " + resultSet.getInt("age"));
           }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
