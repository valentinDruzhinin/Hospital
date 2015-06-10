package controller.commands.humans;

import controller.Controller;
import controller.Checker;
import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Patient;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Register in database patient.
 */
public class PatientRegistration implements Command{

    
    public static final String LOGIN = "regLogin";
    public static final String PASSWORD = "regPassword";
    public static final String FIRST_NAME = "firstName";
    public static final String SECOND_NAME = "secondName";
    public static final String DATE_BIRTH = "dateBirth";
    public static final String DIAGNOSE = "diagnose";
    public static final String ERROR = "error";
    public static final String ERROR_REGISTRATION ="errorRegistration";
    
    
    
    /**
     * Register in database patient by input values.
     * @param request input request
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get input parameters from request
        String regLogin = request.getParameter(PatientRegistration.LOGIN);
        String regPassword = request.getParameter(PatientRegistration.PASSWORD);
        String firstName = request.getParameter(PatientRegistration.FIRST_NAME);
        String secondName = request.getParameter(PatientRegistration.SECOND_NAME);
        Date dateBirth = Date.valueOf(request.getParameter(PatientRegistration.DATE_BIRTH)); 
        String diagnose = request.getParameter(PatientRegistration.DIAGNOSE);
        
        // Check of existing input parameters
        if ((regLogin == null) || (regPassword == null)  || (firstName == null)
                || (secondName == null) || (diagnose == null)) {
            request.setAttribute(PatientRegistration.ERROR, true);
            
        } 
        
        // Checking of existing such login in system
        else if (Checker.checkByLogin(regLogin)) {
            request.setAttribute(PatientRegistration.ERROR_REGISTRATION, true);
            
        } 
        
        // Add new patient to the system
        else {
            Date dateAppoint = new Date(System.currentTimeMillis());
            DaoFactory.
                    getInstance().
                            getPatientDao().create(new Patient
                                                            (regLogin, secondName, firstName, 
                                                                secondName, dateBirth, diagnose, 
                                                                    dateAppoint));
            
        }
        return Controller.DOCTOR;
    }
    
}
