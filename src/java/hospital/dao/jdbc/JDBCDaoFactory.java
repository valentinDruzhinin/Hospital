package hospital.dao.jdbc;

import hospital.dao.DaoFactory;
import hospital.dao.DoctorDao;
import hospital.dao.MedicationDao;
import hospital.dao.NurseDao;
import hospital.dao.OperationDao;
import hospital.dao.PatientDao;
import hospital.dao.ProcedureDao;


/**
 * The factory, which creates the DAO entities.
 */
public class JDBCDaoFactory extends DaoFactory{

    /**
     * The instance of DAO factory
     */
    private static final DaoFactory jdbcFactory = new JDBCDaoFactory(); 
            
    
    private JDBCDaoFactory() {
    }

    
    /**
     * Get the instance of DAO factory.
     * @return DAO factory
     */
    public static DaoFactory getInstance() {
        if (jdbcFactory == null) {
            return JDBCDaoFactory.getInstance();
        } else
            return JDBCDaoFactory.jdbcFactory;
    }
    
    
    @Override
    public DoctorDao getDoctorDao() {
        return (DoctorDao) new JDBCDoctorDao();
    }

    
    @Override
    public MedicationDao getMedicationDao() {
        return (MedicationDao) new JDBCMedicationDao();
    }

    
    @Override
    public NurseDao getNurseDao() {
        return (NurseDao) new JDBCNurseDao();
    }

    
    @Override
    public PatientDao getPatientDao() {
        return (PatientDao) new JDBCPatientDao();
    }

    @Override
    public OperationDao getOperationDao() {
        return  new JDBCOperationDao();
    }

    @Override
    public ProcedureDao getProcedureDao() {
        return new JDBCProcedureDao();
    }
    
}
