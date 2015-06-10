package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * The command which change language in page interface.
 */
public class LanguageCommand implements Command{

    
    public final static String INPUT_LANGUAGE = "lan";
    public final static String LANGUAGE = "language";
    public final static String PAGE = "pageName";
    public final static String MAIN = "main";
    public final static String INDEX = "index";
    public final static String REGISTRATION = "regist";
    public final static String NURSE = "nurse";
    public final static String PATIENT = "patient";
    public final static String DOCTOR = "doctor";
    
    
    
    @Override
    public String execute(HttpServletRequest request) {
        String language = request.getParameter(LanguageCommand.INPUT_LANGUAGE);
        HttpSession session = request.getSession(false);
        session.setAttribute(LanguageCommand.LANGUAGE, language);
        String pageName = request.getParameter(LanguageCommand.PAGE);
        switch (pageName) {
            case LanguageCommand.MAIN:
                return controller.Controller.MAIN;
            case LanguageCommand.INDEX:
                return controller.Controller.INDEX;
            case LanguageCommand.REGISTRATION:
                return controller.Controller.REGISTRATION;
            case LanguageCommand.NURSE:
                return controller.Controller.NURSE;
            case LanguageCommand.PATIENT:
                return controller.Controller.PATIENT;
            case LanguageCommand.DOCTOR:
                return controller.Controller.DOCTOR;
        }
        return null;
    }
    
}
