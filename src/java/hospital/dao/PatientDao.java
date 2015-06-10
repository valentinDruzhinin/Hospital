package hospital.dao;

import hospital.entities.humans.Patient;
import java.util.List;


/**
 *  The functionality to inject the data from PATIENT table in database.
 */
public interface PatientDao {
    
    List<Patient> getAllPatient();
    String getPatientById(Integer id);
    Patient getPatientByLogin(String login);
    void create(Patient patient);
    void update(Patient patient);
    void delete(Integer id);
}
