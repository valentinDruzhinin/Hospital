package controller.commands.humans;

import controller.Controller;
import controller.Checker;
import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.dao.DoctorDao;
import hospital.dao.NurseDao;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Register in database patient or doctor or nurse.
 */
public class RegistrationCommand implements Command{

    
    public static final String TYPE_STAFF = "typeStaff";
    public static final String DOCTOR = "Doctor";
    public static final String NURSE = "Nurse";
    
    /**
     * Create a new user in database by input values.
     * @param request input request
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request
        String regLogin = request.getParameter(PatientRegistration.LOGIN);
        String regPassword = request.getParameter(PatientRegistration.PASSWORD);
        String firstName = request.getParameter(PatientRegistration.FIRST_NAME);
        String secondName = request.getParameter(PatientRegistration.SECOND_NAME);
        Date dateBirth = Date.valueOf(request.getParameter(PatientRegistration.DATE_BIRTH)); 
        String typeStaff = request.getParameter(RegistrationCommand.TYPE_STAFF);
        
        // Checking of existing input parameters
        if ((regLogin == null) || (regPassword == null)  || (firstName == null)
                || (secondName == null) || (typeStaff == null)) {
            request.setAttribute(PatientRegistration.ERROR, true);
            
        } 
        
        // Checking of existing this login in database
        else if (Checker.checkByLogin(regLogin)) {
            request.setAttribute(PatientRegistration.ERROR_REGISTRATION, true);
            
        } 
        
        // Add new user to the system
        else if (typeStaff.equals(RegistrationCommand.DOCTOR)){
            DoctorDao doctor = DaoFactory.getInstance().getDoctorDao();
            java.sql.Date date = new Date(System.currentTimeMillis());
            doctor.create(new Doctor(regLogin,regPassword,firstName,secondName,
                                        dateBirth, date));
            
        } else if(typeStaff.equals(RegistrationCommand.NURSE)) {
            NurseDao nurse = DaoFactory.getInstance().getNurseDao();
            java.sql.Date date = new Date(System.currentTimeMillis());
            nurse.create(new Nurse(regLogin,regPassword,firstName,secondName,
                                        dateBirth, date));
            
        }
        return Controller.REGISTRATION;
    }
    
}
