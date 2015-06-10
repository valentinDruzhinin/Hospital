package controller.commands.manipulations;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;


/**
 * Delete manipulation from database by input identifier.
 */
public class DeleteManipulation implements Command{

    
    /**
     * Delete from database manipulation.
     * @param request input request
     * @return string representation of page
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        // Get parameters from request ...
        String typeManipulation = request.getParameter(ModifyManipulation.MANIPULATION_TYPE);
        String stringId = request.getParameter(ModifyManipulation.MANIPULATION_ID);
        
        // checking :
        // if Id of manipulation are not chosen return to the doctor page.
        if (stringId == null) {
            request.setAttribute(ModifyManipulation.NOT_ID, true);
            return controller.Controller.DOCTOR;
        }
        Integer id = Integer.valueOf(stringId);
        
        // Delete manipulation by Id from database
        switch (typeManipulation) {
            case ModifyManipulation.MEDICATION:
                DaoFactory.getInstance().getMedicationDao().delete(id);
                break;
            case ModifyManipulation.OPERATION:
                DaoFactory.getInstance().getOperationDao().delete(id);
                break;
            case ModifyManipulation.PROCEDURE:
                DaoFactory.getInstance().getProcedureDao().delete(id);
                break;
        }
        
        return controller.Controller.DOCTOR;
    }
    
}
