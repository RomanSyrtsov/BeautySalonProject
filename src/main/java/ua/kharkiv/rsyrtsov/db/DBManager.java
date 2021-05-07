package ua.kharkiv.rsyrtsov.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "ad9c72af";

    public Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager
                .getConnection(URL,LOGIN,PASSWORD);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }

    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con){
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}