package lk.ijse.possystembackend.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private DataSource dataSource;
    private Connection connection;

    private DBConnection() {
        try {
            var initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/simple_pos");
            this.connection = dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }
}
