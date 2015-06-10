package controller.commands.humans;

import controller.commands.Command;
import hospital.dao.DaoFactory;
import hospital.entities.manipulations.Medication;
import hospital.entities.manipulations.Operation;
import hospital.entities.manipulations.Procedure;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


/**
 *  Send to doctor page all manipulations of this patient.
 */
public class PatientHistoryCommand implements Command{

    
    public static final String PATIENT = "patient";
    public static final String PATIENT_MEDICATION = "patientMed";
    public static final String PATIENT_OPERATION = "patientOper";
    public static final String PATIENT_PROCEDURE = "patientProc";
    
    /**
     * Send to doctor page all manipulations which suits for input patient login.
     * @param request input request
     * @return string representation of doctor page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String patient = request.getParameter(PatientHistoryCommand.PATIENT);
        if (patient != null && !patient.isEmpty()) {
            Integer patientId = Integer.parseInt(patient);

            List<Medication> medications = DaoFactory.
                                                getInstance().
                                                    getMedicationDao().
                                                        getMedicationsByPatientId(patientId);

            List<Operation> operations = DaoFactory.
                                                getInstance().
                                                    getOperationDao().
                                                        getOperationByPatientId(patientId);

            List<Procedure> procedures = DaoFactory.
                                                getInstance().
                                                    getProcedureDao().
                                                        getProceduresByPatientId(patientId);
            request.setAttribute(PatientHistoryCommand.PATIENT_MEDICATION, medications);
            request.setAttribute(PatientHistoryCommand.PATIENT_OPERATION, operations);
            request.setAttribute(PatientHistoryCommand.PATIENT_PROCEDURE, procedures);
        }
        return controller.Controller.DOCTOR;
    }

}
