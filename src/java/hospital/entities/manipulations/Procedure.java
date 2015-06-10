package hospital.entities.manipulations;

import java.sql.Date;

/** 
 * The <code>Procedure</code> which appoint <code>Doctor</code> for 
 * <code>Patient</code> and it can perform himself or one <code>Nurse</code>
 * from Nurselist.
 */
public class Procedure extends Manipulation{

    /**
     * Doctor login, which appoint this <code>Procedure</code>.
     */
    private Integer appointerId;
    
    
    public Procedure(Integer id, Date dateManipulation, Integer appointerLogin,
                        Integer performerLogin, Integer patientLogin, 
                            String nameManipulation, String description,
                                Integer isDone) {
        super(id, dateManipulation, performerLogin, patientLogin, nameManipulation,
                    description, isDone);
        this.appointerId = appointerLogin;
    }

    
    public Procedure( Date dateManipulation, Integer appointerLogin, Integer performerLogin,
                            Integer patientLogin, String nameManipulation, 
                                    String description, boolean isDone) {
        super( dateManipulation, performerLogin, patientLogin, nameManipulation, 
                    description, isDone);
        this.appointerId = appointerLogin;
    }

    
    public Integer getAppointerId() {
        return appointerId;
    }

    public void setAppointerId(Integer appointerId) {
        this.appointerId = appointerId;
    }
    
}
