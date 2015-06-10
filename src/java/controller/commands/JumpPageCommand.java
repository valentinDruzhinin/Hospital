package controller.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * The command which jump from page to page.
 */
public class JumpPageCommand implements Command{

    public final static String AIM = "jumpTo";
    private final static String PATIENT = "patientReg";
    private final static String MANIPULATION = "appointManipulation";
    
    
    /**
     * Get the needs page.
     * @param request input request which contain the parameter of need page
     * @return need page path
     */
    @Override
    public String execute(HttpServletRequest request) {
        String pageName = request.getParameter(JumpPageCommand.AIM);
        switch (pageName) {
            case JumpPageCommand.PATIENT:
                return controller.Controller.PATIENT_REGISTRATION;
            case JumpPageCommand.MANIPULATION:
                return controller.Controller.APPOINT_MANIPULATION;
        }
        return null;
    }
    
}
