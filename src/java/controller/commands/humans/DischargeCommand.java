package controller.commands.humans;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Patient;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Discharge current patient from hospital.
 */
public class DischargeCommand implements Command{

    
    public static final String PATIENT = "patient";
    public static final String DISCHARGE = "discharge";
    
    /**
     * Discharge patient and set in database final diagnose and date of discharge.
     * @param request input request which containing patient login 
     *                and final discharge diagnose
     * @return doctor page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request ...
        String patientLogin = request.getParameter(DischargeCommand.PATIENT);
        String discharge = request.getParameter(DischargeCommand.DISCHARGE);
        
        // Discharge patient
        if (patientLogin != null || !patientLogin.isEmpty()) {
            Patient patient = DaoFactory.
                                    getInstance().
                                        getPatientDao().
                                            getPatientByLogin(patientLogin);
            patient.setDateDischarge(new Date(System.currentTimeMillis()));
            patient.setDiagnoseOnFinish(discharge);
            DaoFactory.getInstance().getPatientDao().update(patient);
        }
        return controller.Controller.DOCTOR;
    }
    
}
