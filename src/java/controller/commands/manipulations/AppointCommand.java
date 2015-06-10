package controller.commands.manipulations;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.manipulations.Medication;
import hospital.entities.manipulations.Operation;
import hospital.entities.manipulations.Procedure;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Add new manipulation into database.
 */
public class AppointCommand implements Command{

    
    public static final String APPOINT = "dateAppoint";
    public static final String TYPE = "typeManip";
    public static final String PERFORMER = "performer";
    public static final String PATIENT = "patient";
    public static final String MANIPULATION = "manipulation";
    public static final String DESCRIPTION = "description";
    public static final String ERROR = "errorRegistration";
    public static final String OPERATION = "OPER";
    public static final String MEDICATION = "MEDI";
    public static final String PROCEDURE = "PROC";
    
    
    /**
     * Appoint manipulation into database.
     * @param request input request
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // take parameters from request ...
        Integer performerId = null; 
        Date dateAppoint = Date.valueOf(request.getParameter(AppointCommand.APPOINT)); 
        String typeManipulation = request.getParameter(AppointCommand.TYPE);
        String performer = request.getParameter(AppointCommand.PERFORMER);
        Integer patient = Integer.parseInt(request.getParameter(AppointCommand.PATIENT));
        if (performer != null && !performer.isEmpty()) {
            performerId = Integer.parseInt(performer);
        }
        String manipulation = request.getParameter(AppointCommand.MANIPULATION);
        String description = request.getParameter(AppointCommand.DESCRIPTION);
        HttpSession session = request.getSession(false);
        
        // check if operation do only doctor
        if (typeManipulation.equals(AppointCommand.OPERATION) && performer != null && !performer.isEmpty()) {
            request.setAttribute(AppointCommand.ERROR, true);
        } 
        // create the manipulation in database ...
        else {
            switch (typeManipulation) {
                case AppointCommand.PROCEDURE:
                    DaoFactory.
                        getInstance().
                            getProcedureDao().
                                create(
                                    new Procedure(dateAppoint, (Integer) session.getAttribute("id"),
                                                        performerId, patient, manipulation, description, false));
                    break;
                case AppointCommand.OPERATION:
                    DaoFactory.
                        getInstance().
                            getOperationDao().
                                create(
                                    new Operation(dateAppoint, (Integer) session.getAttribute("id"), 
                                                        patient,manipulation, description, false));
                    break;
                case AppointCommand.MEDICATION:
                    DaoFactory.
                        getInstance().
                            getMedicationDao().
                                create(
                                    new Medication(dateAppoint,(Integer) session.getAttribute("id"),
                                                        performerId, patient, manipulation,
                                                                description, false));
                    break;
            }
        }
        return controller.Controller.DOCTOR;
    }
    
}
