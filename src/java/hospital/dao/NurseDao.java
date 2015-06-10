package hospital.dao;

import hospital.entities.humans.Nurse;
import java.util.List;


/**
 *  The functionality to inject the data from NURSE table in database.
 */
public interface NurseDao {
    List<Nurse> getAllNurses();
    String getNurseById(Integer id);
    Nurse getNurseByLogin(String login);
    void create(Nurse nurse);
    void update(Nurse nurse);
    void delete(Integer id);
}
