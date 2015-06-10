package hospital.dao;

import hospital.entities.manipulations.Medication;
import java.sql.Date;
import java.util.List;


/**
 *  The functionality to inject the data from MEDICATION table in database.
 */
public interface MedicationDao {
    List<Medication> getMedicationsByPatientId(Integer id);
    List<Medication> getMedicationsByPerformerId(Integer id, Date date);
    List<Medication> getMedicationsByAppointedId(Integer id, Date date);
    void create(Medication medication);
    void update(Date date, Integer id);
    void update(boolean isDone, Integer id);
    void update(String description, Integer id);
    void delete(int id);
}
