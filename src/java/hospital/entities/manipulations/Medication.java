package hospital.entities.manipulations;

import java.sql.Date;

/**
 * The <code>Medication</code> which appoint <code>Doctor</code> for 
 * <code>Patient</code> and it can perform himself or one <code>Nurse</code>
 * from Nurselist.
 */
public class Medication extends Manipulation{

    /**
     * Doctor , which appoint this <code>Medication</code>.
     */
    private Integer appointerId;
    
    
    public Medication(Integer id, Date dateManipulation, Integer appointer,
                            Integer performer, Integer patient, 
                                String nameManipulation, String description,
                                    Integer isDone) {
        
        super(id, dateManipulation, performer, patient, nameManipulation,
                    description, isDone);
        this.appointerId = appointer;
    }

    
    public Medication(Date dateManipulation, Integer appointer, 
                            Integer performer, Integer patient,
                                String nameManipulation, String description,
                                    boolean isDone) {
        
        super(dateManipulation, performer, patient, nameManipulation,
                    description, isDone);
        this.appointerId = appointer;
    }

    
    public Integer getAppointerId() {
        return appointerId;
    }

    public void setAppointerId(Integer appointerLogin) {
        this.appointerId = appointerLogin;
    }
    
    
}
