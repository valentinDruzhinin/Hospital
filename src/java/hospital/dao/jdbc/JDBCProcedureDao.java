package hospital.dao.jdbc;

import hospital.dao.ProcedureDao;
import hospital.entities.manipulations.Manipulation;
import hospital.entities.manipulations.Procedure;
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
 * The JDBC implementation of Procedure DAO.
 */
public class JDBCProcedureDao implements ProcedureDao{

    
    /**
     * Get list of procedures by input patient identifier.
     * @param id input identifier
     * @return {@code List} of procedures if which patient has procedures
     *         {@code null} if patient don't has procedures. 
     */
    @Override
    public List<Procedure> getProceduresByPatientId(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PROCEDURES " +
                "WHERE PATIENT = ? AND IS_DONE = 1")) {

            statement.setInt(1, id);
            List<Procedure> procedures = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                procedures.add(
                        new Procedure(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), result.getString(6),
                                            result.getString(7), result.getInt(8)));
            }
            return procedures;
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
     * Get the list of procedures by input performer identifier and date making.
     * @param id input identifier
     * @param date the input value of date
     * @return list of procedures if exist records with such criteria
     *         null if not exist such procedures
     */
    @Override
    public List<Procedure> getProceduresByPerformerId(Integer id, Date date) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PROCEDURES " +
                "WHERE PERFORMER = ? AND DATE_MANIPULATION BETWEEN DATE(?) "
                                  + "AND DATE_ADD(DATE(?), INTERVAL 1 day) "
                                  + "AND IS_DONE = 1")) {

            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setDate(3, date);
            List<Procedure> procedures = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                procedures.add(
                        new Procedure(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), result.getString(6),
                                            result.getString(7), result.getInt(8)));
            }
            return procedures;
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
     * Get the list of procedures from Hospital database by input appointer 
     * identifier and date.
     * @param id input identifier 
     * @param date the input date value
     * @return list of procedures if exist in database records with such login
     *         null otherwise
     */
    @Override
    public List<Procedure> getProceduresByAppointedId(Integer id, Date date) {
       Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PROCEDURES " +
                "WHERE APPOINTED = ? AND PERFORMER IS NULL "
                                  + "AND DATE_MANIPULATION BETWEEN DATE(?) "
                                  + "AND DATE_ADD(DATE(?), INTERVAL 1 day) "
                                  + "AND IS_DONE = 1")) {

            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setDate(3, date);
            List<Procedure> procedures = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                procedures.add(
                new Procedure(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), result.getString(6),
                                            result.getString(7), result.getInt(8)));
            }
            return procedures;
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
     * Create in Hospital database record by input procedure.
     * @param procedure the input procedure.
     */
    @Override
    public void create(Procedure procedure) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO PROCEDURES " +
                            "(DATE_MANIPULATION, APPOINTED, " +
                                "PERFORMER, PATIENT, " +
                            "NAME_MANIPULATION, DESCRIPTION) " +
                         "VALUES(?,?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setDate(1,procedure.getDateManipulation());
            statement.setInt(2,procedure.getAppointerId());
            if(procedure.getPerformerId() == null) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3,procedure.getPerformerId());
            }
            statement.setInt(4,procedure.getPatientId());
            statement.setString(5,procedure.getNameManipulation());
            statement.setString(6,procedure.getDescription());
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
     * Update the record in database by input date and identifier.
     * @param date input date
     * @param id input identifier
     */
    @Override
    public void update(Date date, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE PROCEDURES SET " +
                            "DATE_MANIPULATION = ? " +
                        "WHERE idMANIPULATIONS = ?")) {
            
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
     * Update the record in database by input identifier.
     * @param isDone condition of procedure
     * @param id input identifier of procedure
     */
    @Override
    public void update(boolean isDone, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE PROCEDURES SET " +
                            "IS_DONE = ? " +
                        "WHERE idMANIPULATIONS = ?")) {
            
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
     * Update the record in database by input description and identifier.
     * @param description input description
     * @param id input identifier
     */
    @Override
    public void update(String description, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE PROCEDURES SET " +
                            "DESCRIPTION = ? " +
                        "WHERE idMANIPULATIONS = ?")) {
            
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
     * Delete the procedure from database by input procedure identifier.
     * @param id the input identifier.
     */
    @Override
    public void delete(int id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM PROCEDURES WHERE idMANIPULATIONS = ?")) {
            
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
