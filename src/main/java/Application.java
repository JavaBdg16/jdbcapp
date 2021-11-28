import java.sql.*;

public class Application {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/Contacts?serverTimezone=UTC";

    public static void main(String[] args) {

        System.out.println("Połączenie z bazą danych? " + tryConnect());
        printPersonList();
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

        }
    }
}
