package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Class that contains the methods for connecting to a database using JDBC.*/
public abstract class DBConnection {

    private static final String databaseName = "client_schedule";
    // might need to add "?connectionTimeZone = SERVER" at the end of DB_URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"+databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    static Connection conn;

    /** Establishes a connection with the database.*/
    public static void makeConnection() {
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection open");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /** Returns the connection to the database.*/
    public static Connection getConnection() {
        return conn;
    }

    /** Closes the connection to the database.*/
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
