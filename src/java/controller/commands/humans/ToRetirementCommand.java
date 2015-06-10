package controller.commands.humans;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Send doctor or nurse to retirement.
 */
public class ToRetirementCommand implements Command{

    
    /**
     * Insert current date into database as the date of retirement.
     * @param request input retirement
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request
        String user = (String) request.getSession(false).getAttribute(ChangePasswordCommand.USER);
        String login = (String) request.getSession(false).getAttribute(ChangePasswordCommand.LOGIN);
        
        // For this user set date of retirement in database
        switch(user) {
            case ChangePasswordCommand.DOCTOR:
                if (user.equals(ChangePasswordCommand.DOCTOR)) {
                    Doctor doctor = DaoFactory.getInstance().
                                                        getDoctorDao().
                                                            getDoctorByLogin(login);
                    doctor.setFired(new Date(System.currentTimeMillis()));
                    DaoFactory.getInstance().getDoctorDao().update(doctor);
                }
                break;
            case ChangePasswordCommand.NURSE:
                if (user.equals(ChangePasswordCommand.NURSE)) {
                    Nurse nurse = DaoFactory.getInstance().
                                                        getNurseDao().
                                                            getNurseByLogin(login);
                    nurse.setDateFired(new Date(System.currentTimeMillis()));
                    DaoFactory.getInstance().getNurseDao().update(nurse);
                }
                break;
        }
        return controller.Controller.RETIREMENT;
    }
    
}
