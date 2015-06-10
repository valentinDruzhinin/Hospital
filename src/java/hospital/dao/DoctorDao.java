package hospital.dao;

import hospital.entities.humans.Doctor;


/**
 *  The functionality to inject the data from DOCTOR table in database.
 */
public interface DoctorDao {
    Doctor getDoctorByLogin(String login);
    String getDoctorById(Integer id);
    void create(Doctor doctor);
    void update(Doctor doctor);
    void delete(Integer id);
}
