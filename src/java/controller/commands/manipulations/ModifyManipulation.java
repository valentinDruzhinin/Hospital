package controller.commands.manipulations;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Modify manipulations in database.
 */
public class ModifyManipulation implements Command {

    
    public static final String MANIPULATION_TYPE = "manipType";
    public static final String MANIPULATION_ID = "manipId";
    public static final String NOT_ID = "notSetId";
    public static final String MEDICATION = "medication";
    public static final String OPERATION = "operation";
    public static final String PROCEDURE = "procedure";
    public static final String DESCRIPTION = "modifyDesc";
    public static final String DATE = "modifyDate";
    
    
    /**
     * Insert into database changes.
     * @param request input request
     * @return page for display
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get from request parameters ...
        String typeManipulation = request.getParameter(ModifyManipulation.MANIPULATION_TYPE);
        String manipId = request.getParameter(ModifyManipulation.MANIPULATION_ID);
        
        // check if Id of manipulation is chosen
        if (manipId == null) {
            request.setAttribute(ModifyManipulation.NOT_ID, true);
            return controller.Controller.DOCTOR;
        }
        Integer id = Integer.valueOf(manipId);

        // Modify manipulation
        switch (typeManipulation) {
            case ModifyManipulation.MEDICATION:
                this.modifyMedication(id, request);
                break;
            case ModifyManipulation.OPERATION:
                this.modifyOperation(id, request);
                break;
            case ModifyManipulation.PROCEDURE:
                this.modifyProcedure(id, request);
                break;
        }
        return controller.Controller.DOCTOR;
    }

    
    /**
     * Modify medications in database.
     * @param id input id of medication
     * @param request input request
     */
    private void modifyMedication(Integer id, HttpServletRequest request) {

        String typeToModify = request.getParameter(ModifyManipulation.DESCRIPTION);
        if (typeToModify == null || typeToModify.isEmpty()) {
            typeToModify = request.getParameter(ModifyManipulation.DATE);
            if (typeToModify == null || typeToModify.isEmpty()) {
                DaoFactory.getInstance().getMedicationDao().update(true, id);
            } else {
                Date date = Date.valueOf(typeToModify);
                DaoFactory.getInstance().getMedicationDao().update(date, id);
            }
        } else {
            DaoFactory.getInstance().getMedicationDao().update(typeToModify, id);
        }
    }

    
    /**
     * Modify operation in database.
     * @param id input id of operation
     * @param request input request
     */
    private void modifyOperation(Integer id, HttpServletRequest request) {
        String typeToModify = request.getParameter(ModifyManipulation.DESCRIPTION);
        if (typeToModify == null || typeToModify.isEmpty()) {
            typeToModify = request.getParameter(ModifyManipulation.DATE);
            if (typeToModify == null || typeToModify.isEmpty()) {
                DaoFactory.getInstance().getOperationDao().update(true, id);
            } else {
                Date date = Date.valueOf(typeToModify);
                DaoFactory.getInstance().getOperationDao().update(date, id);
            }
        } else {
            DaoFactory.getInstance().getOperationDao().update(typeToModify, id);
        }
    }

    
    /**
     * Modify procedure in database.
     * @param id input id of procedure
     * @param request input request
     */
    private void modifyProcedure(Integer id, HttpServletRequest request) {

        String typeToModify = request.getParameter(ModifyManipulation.DESCRIPTION);
        if (typeToModify == null || typeToModify.isEmpty()) {
            typeToModify = request.getParameter(ModifyManipulation.DATE);
            if (typeToModify == null || typeToModify.isEmpty()) {
                DaoFactory.getInstance().getProcedureDao().update(true, id);
            } else {
                Date date = Date.valueOf(typeToModify);
                DaoFactory.getInstance().getProcedureDao().update(date, id);
            }
        } else {
            DaoFactory.getInstance().getProcedureDao().update(typeToModify, id);
        }
    }

}
