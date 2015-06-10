package controller.commands.humans;

import controller.Checker;
import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import hospital.entities.humans.Patient;
import javax.servlet.http.HttpServletRequest;


/**
 * Change user password.
 */
public class ChangePasswordCommand implements Command{

    public static final String OLD_PASSWORD = "oldPassword";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String USER = "user";
    public static final String LOGIN = "login";
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String DOCTOR = "DOCTOR";
    public static final String NURSE = "NURSE";
    public static final String PATIENT = "PATIENT";
    
    /**
     * Change password if old password was suitable by this user password 
     * in database.
     * @param request input request which containing old password
     *                and new password 
     * @return password change page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request ...
        String oldPassword = request.getParameter(ChangePasswordCommand.OLD_PASSWORD);
        String newPassword = request.getParameter(ChangePasswordCommand.NEW_PASSWORD);
        String user = (String) request.getSession(false).getAttribute(ChangePasswordCommand.USER);
        String login = (String) request.getSession(false).getAttribute(ChangePasswordCommand.LOGIN);
        
        // change password in database
        switch(user) {
            case ChangePasswordCommand.DOCTOR:
                if (Checker.checkDoctor(login, oldPassword)) {
                    Doctor doctor = DaoFactory.
                                        getInstance().
                                            getDoctorDao().
                                                getDoctorByLogin(login);
                    doctor.setPasswordHash(newPassword);
                    DaoFactory.
                            getInstance().
                                    getDoctorDao().
                                            update(doctor);
                }
                break;
            case ChangePasswordCommand.NURSE:
                if (Checker.checkNurse(login,oldPassword)) {
                    Nurse nurse = DaoFactory.
                                        getInstance().
                                            getNurseDao().
                                                getNurseByLogin(login);
                    nurse.setPasswordHash(newPassword);
                    DaoFactory.
                            getInstance().
                                    getNurseDao().
                                            update(nurse);
                }
                break;
            case ChangePasswordCommand.PATIENT:
                if (Checker.checkPatient(login, oldPassword)) {
                    Patient patient = DaoFactory.
                                            getInstance().
                                                getPatientDao().
                                                    getPatientByLogin(login);
                    patient.setPasswordHash(newPassword);
                    DaoFactory.
                            getInstance().
                                    getPatientDao().
                                            update(patient);
                }
                break;
        }
        return controller.Controller.PASSWORD_CHANGE;
    }
    
}
