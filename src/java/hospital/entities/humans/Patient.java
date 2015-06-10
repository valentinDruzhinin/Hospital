package hospital.entities.humans;

import java.sql.Date;


/**
 *  The <code>Patient</code> which contain <code>Human</code> properties plus
 *  other information.
 */
public class Patient extends Human{
    
    /**
     * The diagnose on entrance of hospital.
     */
    private String diagnoseOnStart;
    
    /**
     * The date of entrance to the hospital.
     */
    private Date dateAppointment;
    
    /**
     * The diagnose on finish of therapy.
     */
    private String diagnoseOnFinish;
    
    /**
     * The date of finishing the therapy.
     */
    private Date dateDischarge;

    
    
    public Patient(Integer id, String login, Integer passwordHash, String firstName, 
                        String secondName, Date dateBirth, String diagnoseOnStart,
                            String diagnoseOnFinish, Date dateAppointment,
                                Date dateDischarge) {
        
        super(id, login, passwordHash, firstName, secondName, dateBirth);
        this.diagnoseOnStart = diagnoseOnStart;
        this.dateAppointment = dateAppointment;
        this.diagnoseOnFinish = diagnoseOnFinish;
        this.dateDischarge = dateDischarge;
    }

    
    public Patient( String login, String password, String firstName, 
                        String secondName, Date dateBirth, String diagnoseOnStart,
                            Date dateAppointment) {
        
        super(login, password, firstName, secondName, dateBirth);
        this.diagnoseOnStart = diagnoseOnStart;
        this.dateAppointment = dateAppointment;
    }

    
    /* Get's and set's methods. */

    public String getDiagnoseOnStart() {
        return diagnoseOnStart;
    }

    public void setDiagnoseOnStart(String diagnoseOnStart) {
        this.diagnoseOnStart = diagnoseOnStart;
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getDiagnoseOnFinish() {
        return diagnoseOnFinish;
    }

    public void setDiagnoseOnFinish(String diagnoseOnFinish) {
        this.diagnoseOnFinish = diagnoseOnFinish;
    }

    public Date getDateDischarge() {
        return dateDischarge;
    }

    public void setDateDischarge(Date dateDischarge) {
        this.dateDischarge = dateDischarge;
    }

    
    
    @Override
    public String toString() {
        return "Patient{" + "Name =" + this.firstName + " Surname=" 
                    + this.secondName + '}';
    }
    
}
