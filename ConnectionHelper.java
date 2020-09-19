/*
 * JDBC connectivity example
 */
package jdbcapplicationdemo;

//Step 1: import the package
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rkforevers This class provide actual database connection using
 * database username,password and URL
 */
public class ConnectionHelper {

    static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";

    //  Database credentials
    static final String USER = "developer";
    static final String PASS = "developer";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //STEP 2: Register JDBC driver
        Class.forName(JDBC_DRIVER);

        //STEP 3: Open a connection
        Connection conn = null;
        
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
        conn.setAutoCommit(false);//This is added to improve the performance of application

        
        return conn;
    }
}
