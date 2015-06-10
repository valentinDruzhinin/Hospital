package hospital.entities.humans;

import java.sql.Date;


/**
 * The <code>Doctor</code> which contain <code>Human</code> properties plus
 * anothers species properties.
 */
public class Doctor extends Human{
    
    /**
     *  Date when <code>Doctor</code> get a job.
     */
    private Date dateEstablished;
    
    /**
     * Date when <code>Doctor</code> were fired.
     */
    private Date dateFired;
    
    

    public Doctor(Integer id, String login, Integer password, String firstName, String secondName,
                        Date dateBirth, Date startWorking, Date fired) {
        super(id, login, password, firstName, secondName, dateBirth);
        this.dateEstablished = startWorking;
        this.dateFired = fired;
    }

    
    public Doctor( String login, String passwordHash, String firstName,
                        String secondName, Date dateBirth, Date dateEstablished) {
        super(login, passwordHash, firstName, secondName, dateBirth);
        this.dateEstablished = dateEstablished;
    }

    
    /* Get's and set's methods */
    
    public Date getFired() {
        return dateFired;
    }

    public void setFired(Date fired) {
        this.dateFired = fired;
    }

    public Date getStartWorking() {
        return dateEstablished;
    }

    public void setStartWorking(Date startWorking) {
        this.dateEstablished = startWorking;
    }
    
    
    @Override
    public String toString() {
        return "Doctor{" + "Name =" + this.firstName + " Surname=" 
                    + this.secondName +'}';
    }
    
    
}
