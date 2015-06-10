package hospital.dao;

import hospital.entities.manipulations.Operation;
import java.sql.Date;
import java.util.List;


/**
 * The functionality to inject the data from OPERATION table in database.
 */
public interface OperationDao {
    List<Operation> getOperationByPatientId(Integer id);
    List<Operation> getOperationByDoctorId(Integer id, Date date);
    void create(Operation operation);
    void update(Date date, Integer id);
    void update(boolean isDone, Integer id);
    void update(String description, Integer id);
    void delete(int id);
}
