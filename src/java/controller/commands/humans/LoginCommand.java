package controller.commands.humans;


import controller.Controller;
import controller.Checker;
import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *  Logining the user if it exist in database.
 */
public class LoginCommand implements Command{

    
    public static final String NULL_PAGE = "nullPage";
    public static final String WRONG_LOGIN = "wrongLogin";
    
    
    
    /**
     * Logining by input values.
     * @param request input request
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request
        String page = null;
        String login = request.getParameter(ChangePasswordCommand.LOGIN);
        String pass = request.getParameter(ChangePasswordCommand.PASSWORD);
        HttpSession session = request.getSession();
        
        // Check of existing login parameter
        if (login.isEmpty() || pass.isEmpty()) {
            request.setAttribute(LoginCommand.NULL_PAGE, true);
            page = Controller.MAIN;
        } 
        
        // Logining if input parameters is true
        else if (Checker.checkDoctor(login, pass)) {
            Doctor doctor = DaoFactory.
                                getInstance().
                                    getDoctorDao().
                                        getDoctorByLogin(login);
            session.setAttribute(ChangePasswordCommand.USER, ChangePasswordCommand.DOCTOR);
            session.setAttribute(ChangePasswordCommand.ID, doctor.getId());
            session.setAttribute(ChangePasswordCommand.LOGIN, doctor.getLogin());
            page = Controller.DOCTOR;
            
        } else if (Checker.checkNurse(login, pass)) {
            Nurse nurse = DaoFactory.
                                getInstance().
                                    getNurseDao().
                                        getNurseByLogin(login);
            session.setAttribute(ChangePasswordCommand.USER, ChangePasswordCommand.NURSE);
            session.setAttribute(ChangePasswordCommand.ID, nurse.getId());
            session.setAttribute(ChangePasswordCommand.LOGIN, nurse.getLogin());
            page = Controller.NURSE;
            
        } else if (Checker.checkPatient(login, pass)) {
            session.setAttribute(ChangePasswordCommand.USER, ChangePasswordCommand.PATIENT);
            session.setAttribute(ChangePasswordCommand.LOGIN, login);
            page = Controller.PATIENT;
            
        } else {
            request.setAttribute(LoginCommand.WRONG_LOGIN, true);
            page = Controller.MAIN;
        }
        
        return page;
    }
    
}
