package hospital.entities.humans;

import java.sql.Date;

/**
 * The abstract <code>Human</code> which contain common properties
 * for <code>Nurse</code>, <code>Doctor</code>, <code>Patient</code>.
 */
public abstract class Human {
    
        /**
         * The identifier of human in database.
         */
        protected Integer id;
        
        /**
        * The Login for this entity.
        */
        protected String login;
        
        /**
         * The hashCode of this entity real password.
         */
        protected Integer passwordHash;
        
        /**
         * The first name of such entity.
         */
        protected String firstName;
        
        /**
         * The second name of such entity.
         */
        protected String secondName;
        
        /**
         * This entity date of Birthday. 
         */
        protected Date dateBirth;

        
    public Human( String login, String password, String firstName, 
                          String secondName, Date dateBirth) {
        this.login = login;
        this.passwordHash = this.hashCode(password);
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateBirth = dateBirth;
    }

    public Human(Integer id, String login, Integer passwordHash, 
                    String firstName, String secondName, Date dateBirth) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateBirth = dateBirth;
    }

    

    /**
     * Transmit String representation of password into Integer hash.
     * @param pas the string password
     * @return hashcode of password
     */
    private final int hashCode(String pas) {
        return pas.hashCode();
    }

    
    
    /* Get and Set methods... */
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = this.hashCode(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
