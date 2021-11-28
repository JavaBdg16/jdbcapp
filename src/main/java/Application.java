import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {

        System.out.println("Połączenie z bazą danych? " + tryConnect());
    }

    /**
     * Zwraca informację, czy udało się połączyć z bazą Contacts
     */
    private static boolean tryConnect() {

        // jdbc:<driver>:<host>/<database-name> username password
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Contacts" +
                        "?serverTimezone=UTC", "root", "Torun2020"
        )) {

            boolean isValid = connection.isValid(2);
            return isValid;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
