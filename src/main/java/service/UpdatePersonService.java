package service;

import app.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdatePersonService {

    public static void contactWithPerson(int personId, Date date) {

        String query = "UPDATE person SET " +
                "contacted_number = contacted_number + ?, " +
                "date_last_contacted = ? " +
                "WHERE id = ?";

        try (Connection connection =
                     DriverManager.getConnection(
                             Application.CONNECTION_URL,
                             "root",
                             "Torun2020");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, 1);
            statement.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
            statement.setInt(3, personId);

            int changeCount = statement.executeUpdate();
            System.out.println("Zmieniono " + changeCount + " rekordów");

        } catch (SQLException ex) {
            System.out.println("ERROR CODE: " + ex.getErrorCode());
            System.out.println("SQL STATE: " + ex.getSQLState());
            ex.printStackTrace();
        }
    }
}
