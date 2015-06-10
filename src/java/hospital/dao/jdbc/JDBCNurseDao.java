package hospital.dao.jdbc;

import hospital.dao.NurseDao;
import hospital.entities.humans.Nurse;
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
 * The implementation of Nurse DAO.
 */
public class JDBCNurseDao implements NurseDao{

    
    /**
     * Get the all working nurses.
     * @return list of nurses from database
     *         null if doesn't exist working nurses
     */
    @Override
    public List<Nurse> getAllNurses() {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(
                                "SELECT * FROM NURSE WHERE WORK_FINISH IS NULL");
            List<Nurse> nurses = new LinkedList<>();
            while(result.next()) {
                
            nurses.add(
                    new Nurse(result.getInt(1), result.getString(2), result.getInt(3),
                                result.getString(4), result.getString(5), result.getDate(6),
                                    result.getDate(7), result.getDate(8)));
                
            }
            return nurses;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex);
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return null;
    }

    
    /**
     * Get the nurse from database by input login value.
     * @param login the input login value
     * @return Nurse if exist nurse with such login
     *         null otherwise
     */
    @Override
    public Nurse getNurseByLogin(String login) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "SELECT * FROM NURSE WHERE LOGIN = ?")) {
            
            statement.setString(1, login);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                return new Nurse(result.getInt(1), result.getString(2), result.getInt(3), 
                                    result.getString(4), result.getString(5), result.getDate(6),
                                        result.getDate(7), result.getDate(8));
            }   
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return null;
    }

    
    /**
     * Get the nurse from database by input identifier.
     * @param id input identifier
     * @return Nurse if exist nurse with such login
     *         null otherwise
     */
    @Override
    public String getNurseById(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "SELECT LOGIN FROM NURSE WHERE id = ?")) {
            
            statement.setInt(1, id);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            if (result.next()) {
                return result.getString(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return null;
    }
    
    
    /**
     * Create the record in database by input nurse.
     * @param nurse the input nurse
     */
    @Override
    public void create(Nurse nurse) {
        
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO NURSE " +
                            "(LOGIN, PASSWORD, FIRST_NAME, " +
                            "SECOND_NAME, DATE_BIRTH, " +
                            "WORK_START, WORK_FINISH) " +
                        "VALUES(?,?,?,?,?,?,?)")) {
            
            connection.setAutoCommit(false);
            
            statement.setString(1,nurse.getLogin());
            statement.setInt(2,nurse.getPasswordHash());
            statement.setString(3,nurse.getFirstName());
            statement.setString(4,nurse.getSecondName());
            statement.setDate(5, nurse.getDateBirth());
            statement.setDate(6, nurse.getDateEstablished());
            statement.setDate(7, nurse.getDateFired());
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    
    /**
     * Update the record in database by input nurse value.
     * @param nurse the input value
     */
    @Override
    public void update(Nurse nurse) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                        "UPDATE NURSE SET " +
                            "PASSWORD = ?,WORK_FINISH = ? " +
                        "WHERE LOGIN = ?")) {
            
            connection.setAutoCommit(false);
            
            statement.setInt(1, nurse.getPasswordHash());
            statement.setDate(2, nurse.getDateFired());
            statement.setString(3, nurse.getLogin());
            statement.executeUpdate();
            
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }

    
    /**
     * Delete from database record by input identifier.
     * @param id input identifier
     */
    @Override
    public void delete(Integer id) {
        Connection connection;
        connection = JDBCConnection.getInstance().getConnection();
        
        try (PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM NURSE WHERE id = ?")) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDoctorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(JDBCNurseDao.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
}
