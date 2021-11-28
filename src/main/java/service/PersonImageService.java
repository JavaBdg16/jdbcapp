package service;

import app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonImageService {

    public static void insertImage(File file, int personId) {

        String query = "INSERT INTO person_image (person_id, data) VALUES (?,?)";

        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            try (Connection connection =
                         DriverManager.getConnection(
                                 Application.CONNECTION_URL,
                                 "root",
                                 "Torun2020"
                         );
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, personId);
                statement.setBinaryStream(2, fileInputStream);

                statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
