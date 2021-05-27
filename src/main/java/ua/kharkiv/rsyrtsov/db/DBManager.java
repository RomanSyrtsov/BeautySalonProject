package ua.kharkiv.rsyrtsov.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    public static final String URL = "jdbc:mysql://localhost:3306/beautysalon";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");

            // ST4DB - the name of data source
            DataSource ds = (DataSource)envContext.lookup("jdbc/BeautySalon");
            con = ds.getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    private DBManager() {
    }


    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}