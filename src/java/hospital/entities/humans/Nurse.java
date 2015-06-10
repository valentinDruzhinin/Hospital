package hospital.entities.humans;

import java.sql.Date;


/**
 * The <code>Nurse</code> which contain <code>Human</code> properties plus
 * anothers species properties.
 */
public class Nurse extends Human{
    
    /**
     * Date when <code>Nurse</code> get a job. 
     */
    private Date dateEstablished;
    
    /**
     * Date when <code>Nurse</code> were fired.
     */
    private Date dateFired;

    
    public Nurse(Integer id, String login, Integer password, String firstName,
                    String secondName, Date dateBirth, Date startWorking, Date fired) {
        super(id, login, password, firstName, secondName, dateBirth);
        this.dateEstablished = startWorking;
        this.dateFired = fired;
    }

    
    public Nurse( String login, String passwordHash, String firstName,
                    String secondName, Date dateBirth, Date dateEstablished) {
        super(login, passwordHash, firstName, secondName, dateBirth);
        this.dateEstablished = dateEstablished;
    }

    
    /* Get's and Set's methods... */ 
    
    public Date getDateEstablished() {
        return dateEstablished;
    }

    public void setDateEstablished(Date dateEstablished) {
        this.dateEstablished = dateEstablished;
    }

    public Date getDateFired() {
        return dateFired;
    }

    public void setDateFired(Date dateFired) {
        this.dateFired = dateFired;
    }
    
    @Override
    public String toString() {
        return "Nurse{" + "Name =" + this.firstName + " Surname=" 
                    + this.secondName + '}';
    }
    
    
}
