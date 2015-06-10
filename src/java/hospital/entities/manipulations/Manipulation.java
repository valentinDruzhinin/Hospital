package hospital.entities.manipulations;

import java.sql.Date;


/**
 * The abstract class which contain all joint arguments of <code>Medication</code>,
 * <code>Operation</code>, <code>Procedure</code>.
 */

public abstract class Manipulation {
    
    /**
     * Identifier in data base table. 
     */
    protected Integer id;
    
    /**
     * The strict date of manipulation.
     */
    protected Date dateManipulation;
    
        
    /**
     * Staff, which must do this <code>Manipulation</code>.
     */
    protected Integer performerId;
    
    /**
     * Patient, whom do this <code>Manipulation</code>.
     */
    protected Integer patientId;
    
    /**
     * Name of <code>Manipulation</code>.
     */
    protected String nameManipulation;
    
    /**
     * Short description of this <code>manipulation</code>.
     */
    protected String description;
    
    /**
     * Condition of manipulation.
     */
    protected boolean isDone;
    
    
    public Manipulation( Integer id, Date dateManipulation, Integer performerLogin,
                                Integer patientLogin, String nameManipulation, 
                                    String description, Integer isDone ) {
        this.id = id;
        this.dateManipulation = dateManipulation;
        this.performerId = performerLogin;
        this.patientId = patientLogin;
        this.nameManipulation = nameManipulation;
        this.description = description;
        this.isDone = Manipulation.isDone(isDone);
    }

    
    public Manipulation(Date dateManipulation, Integer performerLogin, 
                                Integer patientLogin, String nameManipulation, 
                                        String description, boolean isDone) {
        this.dateManipulation = dateManipulation;
        this.performerId = performerLogin;
        this.patientId = patientLogin;
        this.nameManipulation = nameManipulation;
        this.description = description;
        this.isDone = isDone;
    }

    
    /**
     * Get boolean representation of manipulation condition by input 
     * integer representation.
     * @param done input integer condition
     * @return boolean representation of manipulation condition
     */
    public static boolean isDone(Integer done) {
        return (done == 0);
    }
    
    
    /**
     * Get Integer representation of manipulation condition by input 
     * boolean representation.
     * @param done input boolean representation of manipulation condition
     * @return Integer representation of manipulation condition
     */
    public static Integer isDone(boolean done) {
        if (done) {
            return 0;
        } 
        return 1;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateManipulation() {
        return dateManipulation;
    }

    public void setDateManipulation(Date dateManipulation) {
        this.dateManipulation = dateManipulation;
    }

    public Integer getPerformerId() {
        return performerId;
    }

    public void setPerformerId(Integer performerId) {
        this.performerId = performerId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getNameManipulation() {
        return nameManipulation;
    }

    public void setNameManipulation(String nameManipulation) {
        this.nameManipulation = nameManipulation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    
    
    @Override
    public String toString() {
        return "Manipulation{" + "id=" + id + 
                    ", dateManipulation=" + dateManipulation + 
                        ", performerLogin=" + performerId + 
                            ", patientLogin=" + patientId + 
                                ", nameManipulation=" + nameManipulation + 
                                    ", description=" + description + '}';
    }

    
    
}
