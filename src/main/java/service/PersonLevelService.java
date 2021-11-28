package service;

import app.Application;

import java.sql.*;

public class PersonLevelService {

    public static void calculatePersonLevel(int personId) {

        try (Connection connection =
                     DriverManager.getConnection(
                             Application.CONNECTION_URL,
                             "root",
                             "Torun2020");
             CallableStatement statement =
                     connection.prepareCall("{CALL sp_person_level(?,?)}")
        ) {

            statement.setInt(1, personId);
            statement.registerOutParameter(2, Types.VARCHAR);

            statement.execute();

            String personLevel = statement.getString(2);
            System.out.println("Poziom osoby od identyfaktorze " + personId + " = " + personLevel);

        } catch (SQLException ex) {
            System.out.println("ERROR CODE: " + ex.getErrorCode());
            System.out.println("SQL STATE: " + ex.getSQLState());
            ex.printStackTrace();
        }
    }
}
