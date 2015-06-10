package hospital.dao.jdbc;

import hospital.dao.DoctorDao;
import hospital.entities.humans.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * The implementation of Doctor DAO.
 */
public class JDBCDoctorDao implements DoctorDao{

    
    /**
     * Get the <code>Doctor</code> object by input value of login.
     * @param login input login
     * @return <code>Doctor</code> object if exist such login value
     *          <code>null</code> otherwise
     */
    @Override
    public Doctor getDoctorByLogin(String login) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                                "SELECT * FROM DOCTORS WHERE LOGIN = ?")) {
            
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Doctor(result.getInt(1), result.getString(2), result.getInt(3),
                                    result.getString(4), result.getString(5), result.getDate(6),
                                        result.getDate(7), result.getDate(8));
            }    
            
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
        return null;
    }
    
    
    /**
     * Get the <code>Doctor</code> object by input value of id.
     * @param id input identifier value
     * @return <code>Doctor</code> login if exist such id value
     *          <code>null</code> otherwise
     */
    @Override
    public String getDoctorById(Integer id) {
       
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                                "SELECT LOGIN FROM DOCTORS WHERE id = ?")) {
            
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
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
                    Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    
    /**
     * Register the input <code>Doctor</code> in Hospital database.
     * @param doctor the input value of doctor 
     */
    @Override
    public void create(Doctor doctor) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO DOCTORS " +
                            "(LOGIN, PASSWORD, FIRST_NAME, " +
                            "SECOND_NAME, DATE_BIRTH, " +
                            "WORK_START, WORK_FINISH) " +
                         "VALUES(?,?,?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setString(1,doctor.getLogin());
            statement.setInt(2,doctor.getPasswordHash());
            statement.setString(3,doctor.getFirstName());
            statement.setString(4,doctor.getSecondName());
            statement.setDate(5,  doctor.getDateBirth());
            statement.setDate(6,  doctor.getStartWorking());
            statement.setDate(7,  doctor.getFired());
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
     * Update the record in database by input value.
     * @param doctor the input <code>Doctor</code> value
     */
    @Override
    public void update(Doctor doctor) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE DOCTORS SET " +
                            "PASSWORD = ?, WORK_FINISH = ?" +
                        " WHERE id = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setInt(1,doctor.getPasswordHash());
            statement.setDate(2, doctor.getFired());
            statement.setInt(3, doctor.getId());
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
     * DELETE from Hospital database by id value
     * @param id input id
     */
    @Override
    public void delete(Integer id) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM DOCTORS WHERE id = ?")) {
            
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
