package hospital.dao.jdbc;

import hospital.dao.PatientDao;
import hospital.entities.humans.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The implementation of Patient DAO.
 */
public class JDBCPatientDao implements PatientDao{

    
    
    @Override
    public List<Patient> getAllPatient() {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (Statement statement = connection.createStatement()){
            
            statement.executeQuery("SELECT * FROM PATIENTS WHERE DATE_OUT IS NULL");
            
            List<Patient> patients = new LinkedList<>();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                patients.add( 
                        new Patient(result.getInt(1), result.getString(2), result.getInt(3), 
                                        result.getString(4), result.getString(5), result.getDate(6), 
                                            result.getString(7), result.getString(8), result.getDate(9), 
                                                result.getDate(10)) );
            }
            
            return patients;
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    
    /**
     * Get the patient by input login value.
     * @param login the input login value
     * @return Patient if exist in database record with such login
     *         null otherwise
     */
    @Override
    public Patient getPatientByLogin(String login) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                                "SELECT * FROM PATIENTS WHERE LOGIN = ?" )) {
            
            statement.setString(1, login);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                return 
                        new Patient(result.getInt(1), result.getString(2), result.getInt(3), 
                                        result.getString(4), result.getString(5), result.getDate(6), 
                                            result.getString(7), result.getString(8), result.getDate(9), 
                                                result.getDate(10));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    
    /**
     * Get the patient by input identifier.
     * @param id the input identifier
     * @return Patient if exist in database record with such login
     *         null otherwise
     */
    @Override
    public String getPatientById(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                                "SELECT LOGIN FROM PATIENTS WHERE id = ?" )) {
            
            statement.setInt(1, id);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                return result.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    /**
     * Create the record in database by input patient value.
     * @param patient the input patient value
     */
    @Override
    public void create(Patient patient) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO PATIENTS " +
                            "(LOGIN, PASSWORD, FIRST_NAME, " +
                            "SECOND_NAME, DATE_BIRTH, DIAGNOSE_IN, DIAGNOSE_OUT," +
                            "DATE_IN, DATE_OUT) " +
                         "VALUES(?,?,?,?,?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setString(1, patient.getLogin());
            statement.setInt(2, patient.getPasswordHash());
            statement.setString(3, patient.getFirstName());
            statement.setString(4, patient.getSecondName());
            statement.setDate(5, patient.getDateBirth());
            statement.setString(6, patient.getDiagnoseOnStart());
            statement.setString(7, patient.getDiagnoseOnFinish());
            statement.setDate(8, patient.getDateAppointment());
            statement.setDate(9, patient.getDateDischarge());
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    /**
     * Update the record in database by input patient value.
     * @param patient the input patient value.
     */
    @Override
    public void update(Patient patient) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE PATIENTS SET " +
                            "PASSWORD = ?,DIAGNOSE_OUT = ?,DATE_OUT = ?" +
                        " WHERE LOGIN = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setInt(1, patient.getPasswordHash());
            statement.setString(2, patient.getDiagnoseOnFinish());
            statement.setDate(3,  patient.getDateDischarge());
            statement.setString(4, patient.getLogin());
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
    /**
     * Delete from database record by input patient identifier
     * @param id the input patient identifier
     */
    @Override
    public void delete(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM PATIENTS WHERE LOGIN = ?")) {
            
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
                    Logger.getLogger(JDBCPatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
