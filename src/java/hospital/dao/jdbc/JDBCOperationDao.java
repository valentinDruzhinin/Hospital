package hospital.dao.jdbc;

import hospital.dao.OperationDao;
import hospital.entities.manipulations.Manipulation;
import hospital.entities.manipulations.Operation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The implementation of Operation DAO.
 */
public class JDBCOperationDao implements OperationDao{

    
    /**
     * Get from Hospital database list of operations by patient identifier. 
     * @param id input identifier
     * @return list of operations if input record is exist
     *         null otherwise.
     */
    @Override
    public List<Operation> getOperationByPatientId(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM OPERATIONS"
                + " WHERE PATIENT = ? AND IS_DONE = 1")) {

            statement.setInt(1, id);
            List<Operation> operations = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                operations.add(
                    new Operation(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getString(5), 
                                            result.getString(6), result.getInt(7)));
            }
            return operations;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCMedicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCOperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    /**
     * Get the list of operations by input doctor identifier and date.
     * @param id input identifier
     * @param date the date of operations
     * @return list of operations which match to input params
     *         null if not exist such recods
     */
    @Override
    public List<Operation> getOperationByDoctorId(Integer id, Date date) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM OPERATIONS " +
                "WHERE PERFORMER = ? AND "
                    + "DATE BETWEEN DATE(?) AND DATE_ADD(DATE(?), INTERVAL 1 day) "
                                  + "AND IS_DONE = 1")) {

            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setDate(3, date);
            List<Operation> operations = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                operations.add(
                    new Operation(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getString(5), 
                                            result.getString(6), result.getInt(7)));
            }
            return operations;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCMedicationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCOperationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    /**
     * Add the record into OPERATIONS table in Hospital database.
     * @param operation the input operation
     */
    @Override
    public void create(Operation operation) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO OPERATIONS " +
                            "(DATE, PERFORMER, " +
                            "PATIENT, NAME_OPERATION, " +
                            "DESCRIPTION) " +
                         "VALUES(?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setDate(1,operation.getDateManipulation());
            statement.setInt(2,operation.getPerformerId());
            statement.setInt(3,operation.getPatientId());
            statement.setString(4,operation.getNameManipulation());
            statement.setString(5,operation.getDescription());
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    /**
     * Update the operation in Hospital database by input date and identifier.
     * @param date input date
     * @param id input identifier
     */
    @Override
    public void update(Date date, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE OPERATIONS SET " +
                            "DATE = ? " +
                        "WHERE id = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setDate(1, date);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    /**
     * Update the operation in Hospital database by input identifier.
     * @param isDone input condition of Operation
     * @param id input identifier
     */
    @Override
    public void update(boolean isDone, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE OPERATIONS SET " +
                            "IS_DONE = ? " +
                        "WHERE id = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setInt(1, Manipulation.isDone(isDone));
            statement.setInt(2, id);
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    

    /**
     * Update the operation in Hospital database by input description and identifier.
     * @param description input description
     * @param id input identifier
     */
    @Override
    public void update(String description, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE OPERATIONS SET " +
                            "DESCRIPTION = ? " +
                        "WHERE id = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setString(1, description);
            statement.setInt(2, id);
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    /**
     * Delete operation from Hospital database by input identifier.
     * @param id the input identifier
     */
    @Override
    public void delete(int id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM OPERATIONS WHERE id = ?")) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
