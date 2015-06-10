package hospital.dao;

import hospital.entities.manipulations.Procedure;
import java.sql.Date;
import java.util.List;


/**
 *  The functionality to inject the data from PROCEDURE table in database.
 */
public interface ProcedureDao {
    List<Procedure> getProceduresByPatientId(Integer id);
    List<Procedure> getProceduresByPerformerId(Integer id, Date date);
    List<Procedure> getProceduresByAppointedId(Integer id, Date date);
    void create(Procedure procedure);
    void update(Date date, Integer id);
    void update(String description, Integer id);
    void update(boolean isDone, Integer id);
    void delete(int id);
}
