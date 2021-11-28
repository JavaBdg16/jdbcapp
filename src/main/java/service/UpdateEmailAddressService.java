package service;

import app.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEmailAddressService {

    public static void update(int emailAddressId, String emailAddress) {
        String query = "UPDATE email_address SET email_address = ? WHERE id = ?";

        try (Connection connection =
             DriverManager.getConnection(Application.CONNECTION_URL, "root", "Torun2020");
             PreparedStatement statement = connection.prepareStatement(query)
        ) {

            statement.setString(1, emailAddress);
            statement.setInt(2, emailAddressId);

            int changeCount = statement.executeUpdate();
            System.out.println("Zaktualizowano " + changeCount + " rekordów");

        } catch (SQLException ex) {
            System.out.println("ERROR CODE: " + ex.getErrorCode());
            System.out.println("SQL STATE: " + ex.getSQLState());
            ex.printStackTrace();
        }
    }
}
