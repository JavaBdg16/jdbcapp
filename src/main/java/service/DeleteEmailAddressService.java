package service;

import app.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmailAddressService {

    public static void delete(int emailAddressId) {
        String query = "DELETE FROM email_address WHERE id = ?";

        try (Connection connection =
                     DriverManager.getConnection(Application.CONNECTION_URL, "root", "Torun2020");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, emailAddressId);

            int changeCount = statement.executeUpdate();
            System.out.println("Usunięto " + changeCount + " rekordów");

        } catch (SQLException ex) {
            System.out.println("ERROR CODE: " + ex.getErrorCode());
            System.out.println("SQL STATE: " + ex.getSQLState());
            ex.printStackTrace();
        }
    }
}
