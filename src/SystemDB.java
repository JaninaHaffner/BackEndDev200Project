import java.sql.*;

public class SystemDB {

    static Connection connection;
    static ResultSet accountInfo;
    static Statement statement;
    static int rowsAffected;
    static  long accountNumber;

    /* method to get connection to database
     * try catch block to handle exceptions
     * get the connection using the DriverManager
     * if statement to check if connection was successful and else to display message if no connection failed.
     * return the connection
     * All sql queries are processed in this class */

    private static Connection systemDBConnect() {
        Connection connection;

        String dbURL = "jdbc:mysql://localhost:3306/dev200";
        String dbUserName = "otheruser";
        String dbPassword = "swordfish";

        try {

            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    public static int SavedNewAccount(long accountId, String accountType, long balance, long overdraft) {
        try {
            connection = SystemDB.systemDBConnect();
            assert connection != null;
            statement = connection.createStatement();

            ResultSet projectNrResult = statement.executeQuery("SELECT accountNumber FROM accounts");
            int accountNrLast = 0;

            while (projectNrResult.next()){

                accountNrLast = projectNrResult.getInt("accountNumber");
            }
            accountNumber = accountNrLast + 1;

            rowsAffected = statement.executeUpdate(
                    "INSERT INTO accounts VALUES ('" + accountNumber + "', '" + accountId + "', '" + accountType + "', '" + balance + "'," +
                            " '" + overdraft + "')"
            );

        } catch (SQLException e) {
            System.out.println("Account could not be loaded.");
        }
        return rowsAffected;
    }
    public static ResultSet CheckAccountInformation(long accountId) {
        try {
            connection = SystemDB.systemDBConnect();
            assert connection != null;
            statement = connection.createStatement();

            accountInfo = statement.executeQuery("SELECT * FROM accounts " +
                    "WHERE accountId = ('" + accountId + "')");

        } catch (SQLException e) {
            System.out.println("Account could not be found.");
        }
        return accountInfo;
    }

    public static int UpdateAccountBalance(long accountId, long newBalance){
        try {
            connection = SystemDB.systemDBConnect();
            assert connection != null;
            statement = connection.createStatement();

            rowsAffected = statement.executeUpdate(
                    "UPDATE accounts SET balance = ('" + newBalance + "') " +
                            "WHERE accountId = ('" + accountId + "')"
            );
        } catch (SQLException e) {
            System.out.println("Your account could not be updated.");
        }
        return rowsAffected;
    }
}
