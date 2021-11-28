import entity.EmailAddress;
import entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/Contacts?serverTimezone=UTC";

    public static void main(String[] args) {

        System.out.println("Połączenie z bazą danych? " + tryConnect());
        printPersonList();
        List<Person> person = findPersonsByContactedNumber(1, 10);
        person.forEach(System.out::println);

//        List<EmailAddress> emailAddresses = findEmailAddressByPersonId(1);
//        emailAddresses.forEach(System.out::println);
    }

    /**
     * Zwraca informację, czy udało się połączyć z bazą Contacts
     */
    private static boolean tryConnect() {

        // jdbc:<driver>:<host>/<database-name> username password
        try (Connection connection = DriverManager.getConnection(
                CONNECTION_URL, "root", "Torun2020"
        )) {

            boolean isValid = connection.isValid(2);
            return isValid;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private static void printPersonList() {

        String query = "SELECT " +
                "id, " +
                "first_name, " +
                "last_name, " +
                "contacted_number, " +
                "date_last_contacted, " +
                "date_added " +
                "FROM person";

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_URL, "root", "Torun2020");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query)) {

            System.out.println("Lista po indeksie:");

            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1));
                System.out.print(" | ");
                System.out.print(resultSet.getString(2));
                System.out.print(" | ");
                System.out.print(resultSet.getString(3));
                System.out.print(" | ");
                System.out.print(resultSet.getInt(4));
                System.out.print(" | ");
                System.out.print(resultSet.getDate(5));
                System.out.print(" | ");
                System.out.print(resultSet.getDate(6));

                System.out.println();
            }

            System.out.println("Lista po nazwie kolumn:");

            while (resultSet1.next()) {
                System.out.print(resultSet1.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet1.getString("first_name"));
                System.out.print(" | ");
                System.out.print(resultSet1.getString("last_name"));
                System.out.print(" | ");
                System.out.print(resultSet1.getInt("contacted_number"));
                System.out.print(" | ");
                System.out.print(resultSet1.getDate("date_last_contacted"));
                System.out.print(" | ");
                System.out.print(resultSet1.getDate("date_added"));

                System.out.println();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static List<Person> findPersonsByContactedNumber(int min, int max) {

        String query = "SELECT " +
                "id, " +
                "first_name, " +
                "last_name, " +
                "contacted_number, " +
                "date_last_contacted, " +
                "date_added " +
                "FROM person " +
                "WHERE contacted_number BETWEEN ? AND ?";

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_URL, "root", "Torun2020");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, min);
            statement.setInt(2, max);

            try (ResultSet resultSet = statement.executeQuery()) {

                List<Person> result = new ArrayList<>();
                while (resultSet.next()) {
                    Person person = new Person();

                    person.setId(resultSet.getInt("id"));
                    person.setFirstName(resultSet.getString("first_name"));
                    person.setLastName(resultSet.getString("last_name"));
                    person.setContactedNumber(resultSet.getInt("contacted_number"));
                    person.setLastContactedDate(resultSet.getDate("date_last_contacted"));
                    person.setDateAdded(resultSet.getDate("date_added"));
                    person.setEmailAddresses(findEmailAddressByPersonId(person.getId()));

                    result.add(person);
                }

                return result;

            } catch (SQLException ex) {
                throw ex;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static List<EmailAddress> findEmailAddressByPersonId(int personId) {

        String query = "SELECT " +
                "id, " +
                "person_id, " +
                "email_address " +
                "FROM email_address " +
                "WHERE person_id = ?";

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_URL, "root", "Torun2020");
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, personId);

            ResultSet resultSet = statement.executeQuery();

            List<EmailAddress> result = new ArrayList<>();
            while (resultSet.next()) {
                EmailAddress emailAddress = new EmailAddress();
                emailAddress.setEmailAddress(resultSet.getString("email_address"));
                emailAddress.setId(resultSet.getInt("id"));
                emailAddress.setPersonId(resultSet.getInt("person_id"));

                result.add(emailAddress);
            }

            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
