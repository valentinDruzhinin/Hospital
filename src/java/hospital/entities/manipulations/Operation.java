package hospital.entities.manipulations;

import java.sql.Date;

/**
 * The <code>Operation</code> which appoint <code>Doctor</code> for 
 * <code>Patient</code> and do it himself.
 */
public class Operation extends Manipulation{
    
    
    public Operation(Integer id, Date dateManipulation, Integer performerLogin, 
                        Integer patientLogin, String nameManipulation, 
                            String description, Integer isDone) {
        
        super(id, dateManipulation, performerLogin, patientLogin, nameManipulation,
                    description, isDone);
    }

    
    public Operation(Date dateManipulation, Integer performerLogin, Integer patientLogin,
                        String nameManipulation, String description, boolean isDone) {
        
        super(dateManipulation, performerLogin, patientLogin, nameManipulation,
                    description, isDone);
    }

    
}
