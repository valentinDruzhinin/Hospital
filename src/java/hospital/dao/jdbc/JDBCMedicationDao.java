package hospital.dao.jdbc;

import hospital.dao.MedicationDao;
import hospital.entities.manipulations.Manipulation;
import hospital.entities.manipulations.Medication;
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
 * The JDBC implementation of MedicationDao.
 */
public class JDBCMedicationDao implements MedicationDao{

    
    /**
     * Get the list of medications by input patient id.
     * @param id patient id
     * @return list of medications if exist medications with such patient login
     *         null otherwise
     */
    @Override
    public List<Medication> getMedicationsByPatientId(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM MEDICATIONS " +
                "WHERE PATIENT = ? AND IS_DONE = 1")) {

            statement.setInt(1, id);
            List<Medication> medications = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                medications.add(
                    new Medication(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), 
                                            result.getString(6), result.getString(7),
                                                result.getInt(8)));
            }
            return medications;
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
     * Get list of medications by input performer id and date of it making.
     * @param id input performer identifier
     * @param date the input date
     * @return list of medications if exist records in database with such 
     *              performer login and date of making
     *         null otherwise
     */
    @Override
    public List<Medication> getMedicationsByPerformerId(Integer id, Date date) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM MEDICATIONS " +
                "WHERE PERFORMER = ? AND DATE BETWEEN DATE(?) "
                                  + "AND DATE_ADD(DATE(?), INTERVAL 1 day) "
                                  + "AND IS_DONE = 1")) {

            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setDate(3, date);
            List<Medication> medications = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                medications.add(
                    new Medication(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), result.getString(6),
                                            result.getString(7), result.getInt(8)));
            }
            return medications;
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
     * Get the list of medications which must done doctor with 
     * input identifier in input date.
     * @param id input identifier
     * @param date the input date value
     * @return list of medications if exist records in database by such 
     *              appointer login
     *         null otherwise
     */
    @Override
    public List<Medication> getMedicationsByAppointedId(Integer id, Date date) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM MEDICATIONS " +
                "WHERE APPOINTED = ? AND PERFORMER IS NULL "
                                  + "AND DATE BETWEEN DATE(?) "
                                  + "AND DATE_ADD(DATE(?), INTERVAL 1 day) "
                                  + "AND IS_DONE = 1")) {

            statement.setInt(1, id);
            statement.setDate(2, date);
            statement.setDate(3, date);
            List<Medication> medications = new LinkedList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                medications.add(
                    new Medication(result.getInt(1), result.getDate(2), result.getInt(3),
                                        result.getInt(4), result.getInt(5), result.getString(6),
                                            result.getString(7), result.getInt(8)));
            }
            return medications;
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
     * Create the record in database by input medication.
     * @param medication the input medication.
     */
    @Override
    public void create(Medication medication) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO MEDICATIONS " +
                            "(DATE, APPOINTED, " +
                                "PERFORMER, PATIENT, " +
                            "NAME_MEDICATION, DESCRIPTION) " +
                         "VALUES(?,?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setDate(1, medication.getDateManipulation());
            statement.setInt(2, medication.getAppointerId());
            if (medication.getPerformerId() == null) {
                statement.setNull(3, java.sql.Types.INTEGER);
            } else {
                statement.setInt(3, medication.getPerformerId());
            }
            statement.setInt(4, medication.getPatientId());
            statement.setString(5, medication.getNameManipulation());
            statement.setString(6, medication.getDescription());
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
     * Update the record in database by input Date and identifier.
     * @param date input date
     * @param id input identifier
     */
    @Override
    public void update(Date date, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE MEDICATIONS SET " +
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
     * Update the record in database by input identifier.
     * @param isDone input condition of medication
     * @param id input identifier
     */
    @Override
    public void update(boolean isDone, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE MEDICATIONS SET " +
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
     * Update the record in database by input description and identifier.
     * @param description input description
     * @param id input identifier
     */
    @Override
    public void update(String description, Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE MEDICATIONS SET " +
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
     * Delete the record in database by input identifier.
     * @param id the input identifier
     */
    @Override
    public void delete(int id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM MEDICATIONS WHERE id = ?")) {
            
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
