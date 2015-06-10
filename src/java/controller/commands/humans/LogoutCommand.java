package controller.commands.humans;

import controller.commands.Command;
import javax.servlet.http.HttpServletRequest;


/**
 *  Logout from users cabinets and invalidate session.
 */
public class LogoutCommand implements Command{

    
    /**
     * Finish the exist session.
     * @param request input request
     * @return string representation of main page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        request.getSession(false).invalidate();
      
        return controller.Controller.MAIN;
    }
    
}
