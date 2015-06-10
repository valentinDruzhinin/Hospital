package hospital.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The object, which return the connection to Hospital database.
 */
public class JDBCConnection {
    
    /**
     * <b>Connection</b> to database.
     */
    private Connection connection ;
    
    /**
     *  The instance of this class.
     */
    private static JDBCConnection instance = new JDBCConnection();
    
    
    /**
     * Constructor of database connection.
     */
    private JDBCConnection(){
        try {
           connection  = createConnection();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Get the instance of JDBC connection.
     * @return connection to database.
     */
    public static JDBCConnection getInstance(){
        if( instance == null ){
            synchronized (JDBCConnection.class) {
                if (instance == null) {
                    instance = new JDBCConnection();
                }
            }
        }
        return instance;
    }
    
    
    /**
     * Get the <code>Connection</code> to database.
     * @return connection to database.
     */
    public Connection getConnection(){
        try {
            if (connection == null) {
                connection = createConnection();
            } else if( !connection.isValid(1)){
                connection.close();
                connection  = createConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
        
    
    /**
     * Create the database connection.
     * @return database connection.
     * @throws SQLException 
     */ 
    private Connection createConnection() throws SQLException {
        try {    
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital",
                    "root", "root");
    }

} 