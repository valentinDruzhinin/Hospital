package controller;

import hospital.dao.DaoFactory;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import hospital.entities.humans.Patient;


/**
 * The checker which checks the input parameters of existing in database.
 */
public class Checker {
    
    
    /**
     * Check the existing in database doctor.
     * @param enterLogin input doctor login
     * @param enterPassword input doctor password
     * @return  true if exist in database
     *          false otherwise
     */
    public static boolean checkDoctor(String enterLogin, String enterPassword){
        DaoFactory factory = DaoFactory.getInstance();
        
        Doctor doctor = factory.getDoctorDao().getDoctorByLogin(enterLogin);
        return (doctor  != null) && 
                    doctor.getLogin().equals(enterLogin) &&
                        doctor.getPasswordHash().equals(enterPassword.hashCode()) && 
                            (doctor.getFired() == null);
    }
    
    
    /**
     * Check the existing in database nurse.
     * @param enterLogin input nurse login
     * @param enterPassword input nurse password
     * @return  true if exist in database
     *          false otherwise
     */ 
    public static boolean checkNurse(String enterLogin, String enterPassword){
        DaoFactory factory = DaoFactory.getInstance();
        
        Nurse nurse = factory.getNurseDao().getNurseByLogin(enterLogin);
        return (nurse  != null) && 
                    nurse.getLogin().equals(enterLogin) &&
                        nurse.getPasswordHash().equals(enterPassword.hashCode());
    }
    
    
    /**
     * Check the existing in database patient.
     * @param enterLogin input patient login
     * @param enterPassword input patient password
     * @return  true if exist in database
     *          false otherwise
     */
    public static boolean checkPatient(String enterLogin, String enterPassword){
        DaoFactory factory = DaoFactory.getInstance();
        
        Patient patient = factory.getPatientDao().getPatientByLogin(enterLogin);
        
        return  (patient  != null) && 
                    (patient.getLogin().equals(enterLogin)) &&
                        patient.getPasswordHash().equals(enterPassword.hashCode());
    }
    
    
    /**
     * Check the existing in system user with such login
     * @param enterLogin user login
     * @return true if exist 
     *         false otherwise
     */
    public static boolean checkByLogin(String enterLogin){
        DaoFactory factory = DaoFactory.getInstance();
        
        Doctor doctor = factory.getDoctorDao().getDoctorByLogin(enterLogin);
        if( (doctor  != null) && 
                doctor.getLogin().equals(enterLogin)) {
            return true;
        }
        
        Nurse nurse = factory.getNurseDao().getNurseByLogin(enterLogin);
        if((nurse  != null) && 
                nurse.getLogin().equals(enterLogin))
            return true;
        
        Patient patient = factory.getPatientDao().getPatientByLogin(enterLogin);
        return (patient != null) && 
                        patient.getLogin().equals(enterLogin);
    }
    
}
