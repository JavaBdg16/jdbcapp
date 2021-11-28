package service;

import app.Application;

import java.sql.*;

public class InsertTransactionService {

    public static void addPerson(String firstName, String lastName, String emailAddress) {
        String queryPerson = "INSERT INTO person " +
                "(first_name, last_name, contacted_number, date_last_contacted, date_added) " +
                "VALUES (?, ?, ?, ?, ?)";

        String emailAddressQuery = "INSERT INTO email_address (person_id, email_address) " +
                "VALUES (?, ?)";

        int personId = 0;

        Connection connection = null;

        try {

            connection =
                    DriverManager.getConnection(
                            Application.CONNECTION_URL,
                            "root",
                            "Torun2020");
            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 queryPerson,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, 0);
                statement.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
                statement.setDate(5, new java.sql.Date(new java.util.Date().getTime()));

                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {

                    if (resultSet.next()) {
                        personId = resultSet.getInt(1);
                    }

                } catch (SQLException ex) {
                    throw ex;
                }

            } catch (SQLException ex) {
                throw ex;
            }

            if (personId > 0) {
                try (PreparedStatement statement =
                             connection.prepareStatement(emailAddressQuery)) {

                    statement.setInt(1, personId);
                    statement.setString(2, emailAddress);

                    statement.executeUpdate();

                } catch (SQLException ex) {
                    throw ex;
                }
            }

            connection.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();

            if (connection != null) {
                try {
                    System.out.println(ex.getMessage() + " ROLLBACK");
                    System.out.println("ERROR CODE: " + ex.getErrorCode());
                    System.out.println("SQL STATE: " + ex.getSQLState());
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
