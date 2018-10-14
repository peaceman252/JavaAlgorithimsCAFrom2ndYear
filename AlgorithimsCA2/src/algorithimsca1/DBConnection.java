package algorithimsca1;

/**
 *
 * @author n00143569
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//class for database connection 
public class DBConnection {
    //instance variables
    private static Connection sConnection;
    //creates the connection
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        //host = "daneel";
        host = "localhost";
        db = "n00143569";
        user = "N00143569";
        password = "N00143569";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }

        return sConnection;
    }
}
