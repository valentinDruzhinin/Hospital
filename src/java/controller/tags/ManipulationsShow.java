package controller.tags;

import controller.commands.JumpPageCommand;
import hospital.dao.DaoFactory;
import hospital.entities.humans.Doctor;
import hospital.entities.humans.Nurse;
import hospital.entities.humans.Patient;
import hospital.entities.manipulations.Medication;
import hospital.entities.manipulations.Operation;
import hospital.entities.manipulations.Procedure;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 *  Get from database all doctor manipulations by input date.
 */
public class ManipulationsShow extends BodyTagSupport {

    
    /**
     * Creates new instance of tag handler
     */
    public ManipulationsShow() {
        super();
    }

    
    /**
     * Method called from doStartTag(). Fill in this method to perform other
     * operations from doStartTag().
     */
    private void otherDoStartTagOperations() {
        HttpSession session = this.pageContext.getSession();
        
        if (session.getAttribute("user").equals("DOCTOR")) {
            
            Date date = new Date (System.currentTimeMillis());
            String login = (String) session.getAttribute("login");
            Doctor doctor = DaoFactory.
                                getInstance().
                                        getDoctorDao().
                                                getDoctorByLogin(login);
            
            List<Medication> medications = DaoFactory.
                                                getInstance().
                                                    getMedicationDao().
                                                        getMedicationsByAppointedId(doctor.getId(), date);
            
            List<Procedure> procedures = DaoFactory.
                                            getInstance().
                                                getProcedureDao().
                                                    getProceduresByAppointedId(doctor.getId(), date);
            
            List<Operation> operations = DaoFactory.
                                            getInstance().
                                                getOperationDao().
                                                    getOperationByDoctorId(doctor.getId(), date);
            
            ServletRequest request = this.pageContext.getRequest();
            request.setAttribute("medications", medications);
            request.setAttribute("procedures", procedures);
            request.setAttribute("operations", operations);
            try {
                if (request.getParameter(JumpPageCommand.AIM) == null) {
                    request.getRequestDispatcher(controller.Controller.DOCTOR).forward(request, 
                                                            this.pageContext.getResponse());
                } else {
                    request.getRequestDispatcher(controller.Controller.APPOINT_MANIPULATION).forward(request, 
                                                            this.pageContext.getResponse());
                }
            } catch (ServletException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (session.getAttribute("user").equals("NURSE")) {
            
            Date date = new Date (System.currentTimeMillis());
            String login = (String) session.getAttribute("login");
            Nurse nurse = DaoFactory.getInstance().getNurseDao().getNurseByLogin(login);
            
            List<Medication> medications = DaoFactory.
                                                getInstance().
                                                    getMedicationDao().
                                                        getMedicationsByPerformerId(nurse.getId(), date);
            
            List<Procedure> procedures = DaoFactory.
                                                getInstance().
                                                    getProcedureDao().
                                                        getProceduresByPerformerId(nurse.getId(), date);
            
            ServletRequest request = this.pageContext.getRequest();
            request.setAttribute("medications", medications);
            request.setAttribute("procedures", procedures);
            try {
                request.getRequestDispatcher(controller.Controller.NURSE).forward(request, 
                                                        this.pageContext.getResponse());
            } catch (ServletException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (session.getAttribute("user").equals("PATIENT")){
            
            Date date = new Date (System.currentTimeMillis());
            String login = (String) session.getAttribute("login");
            Patient patient = DaoFactory.getInstance().getPatientDao().getPatientByLogin(login);
            
            List<Medication> medications = DaoFactory.
                                                getInstance().
                                                    getMedicationDao().
                                                        getMedicationsByPatientId(patient.getId());
            
            List<Procedure> procedures = DaoFactory.
                                            getInstance().
                                                getProcedureDao().
                                                    getProceduresByPatientId(patient.getId());
            
            List<Operation> operations = DaoFactory.
                                            getInstance().
                                                getOperationDao().
                                                    getOperationByPatientId(patient.getId());
            
            ServletRequest request = this.pageContext.getRequest();
            request.setAttribute("medications", medications);
            request.setAttribute("procedures", procedures);
            request.setAttribute("operations", operations);
            try {
                request.getRequestDispatcher(controller.Controller.PATIENT).forward(request, 
                                                        this.pageContext.getResponse());
            } catch (ServletException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManipulationsShow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    /**
     * Method called from doEndTag() Fill in this method to perform other
     * operations from doEndTag().
     */
    private void otherDoEndTagOperations() {
    }

    
    /**
     * Fill in this method to process the body content of the tag. You only need
     * to do this if the tag's BodyContent property is set to "JSP" or
     * "tagdependent." If the tag's bodyContent is set to "empty," then this
     * method will not be called.
     */
    private void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        // TODO: insert code to write html before writing the body content.
        // e.g.:
        //
        // out.println("<strong>" + attribute_1 + "</strong>");
        // out.println("   <blockquote>");

        // write the body content (after processing by the JSP engine) on the output Writer
        bodyContent.writeOut(out);

        // Or else get the body content as a string and process it, e.g.:
        //     String bodyStr = bodyContent.getString();
        //     String result = yourProcessingMethod(bodyStr);
        //     out.println(result);
        // TODO: insert code to write html after writing the body content.
        // e.g.:
        //
        // out.println("   </blockquote>");
        // clear the body content for the next time through.
        bodyContent.clearBody();
    }

   
    /**
     * This method is called when the JSP engine encounters the start tag, after
     * the attributes are processed. Scripting variables (if any) have their
     * values set here.
     *
     * @return EVAL_BODY_BUFFERED if the JSP engine should evaluate the tag
     * body, otherwise return SKIP_BODY. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public int doStartTag() throws JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_BUFFERED;
        } else {
            return SKIP_BODY;
        }
    }

    
    /**
     * This method is called after the JSP engine finished processing the tag.
     *
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP
     * page, otherwise return SKIP_PAGE. This method is automatically generated.
     * Do not modify this method. Instead, modify the methods that this method
     * calls.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public int doEndTag() throws JspException {
        otherDoEndTagOperations();
        
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }

    
    /**
     * This method is called after the JSP engine processes the body content of
     * the tag.
     *
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body
     * again, otherwise return SKIP_BODY. This method is automatically
     * generated. Do not modify this method. Instead, modify the methods that
     * this method calls.
     * @throws javax.servlet.jsp.JspException
     */
    @Override
    public int doAfterBody() throws JspException {
        try {
            // This code is generated for tags whose bodyContent is "JSP"
            BodyContent bodyCont = getBodyContent();
            JspWriter out = bodyCont.getEnclosingWriter();
            
            writeTagBodyContent(out, bodyCont);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }
        
        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    
    /**
     * Handles exception from processing the body content.
     */
    private void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("Error in ManipulationsShow tag", ex);
    }

    
    /**
     * Fill in this method to determine if the rest of the JSP page should be
     * generated after this tag is finished. Called from doEndTag().
     */
    private boolean shouldEvaluateRestOfPageAfterEndTag() {
        // TODO: code that determines whether the rest of the page
        //       should be evaluated after the tag is processed
        //       should be placed here.
        //       Called from the doEndTag() method.
        //
        return true;
    }

    
    /**
     * Fill in this method to determine if the tag body should be evaluated
     * again after evaluating the body. Use this method to create an iterating
     * tag. Called from doAfterBody().
     */
    private boolean theBodyShouldBeEvaluatedAgain() {
        // TODO: code that determines whether the tag body should be
        //       evaluated again after processing the tag
        //       should be placed here.
        //       You can use this method to create iterating tags.
        //       Called from the doAfterBody() method.
        //
        return false;
    }

    
    private boolean theBodyShouldBeEvaluated() {
        // TODO: code that determines whether the body should be
        //       evaluated should be placed here.
        //       Called from the doStartTag() method.
        return false;
    }
    
}
