package hospital.dao;

import hospital.dao.jdbc.JDBCDaoFactory;


/**
 * The factory of DAO entities. 
 */
public abstract class DaoFactory {
    
    public abstract DoctorDao getDoctorDao();
    public abstract NurseDao getNurseDao();
    public abstract PatientDao getPatientDao();
    public abstract MedicationDao getMedicationDao();
    public abstract OperationDao getOperationDao();
    public abstract ProcedureDao getProcedureDao();
    
    public static DaoFactory getInstance(){
        return JDBCDaoFactory.getInstance();
    }
    
    
}
