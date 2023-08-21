package com.niveus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDOperations {


	static final String DB_URL = "jdbc:postgresql://34.69.253.26:5432/postgres";
	static final String DB_USER = "postgres";
    static final String DB_PASSWORD = "Aqua888";

    public static void insertData(Connection connection, int id, String name, int age) throws SQLException {
        String insertQuery = "INSERT INTO \"user\" (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        }
    }

    public static ResultSet retrieveData(Connection connection) throws SQLException {
    	String selectQuery = "SELECT id, name, age FROM \"user\"";
    	PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
    	ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet;
    }

    public static void updateData(Connection connection, int id, String newName, int newAge) throws SQLException {
        String updateQuery = "UPDATE \"user\" SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newAge);
            preparedStatement.setInt(3, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("No data updated.");
            }
        }
    }


    public static void deleteData(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM \"user\" WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("No data deleted.");
            }
        }
    }
}
