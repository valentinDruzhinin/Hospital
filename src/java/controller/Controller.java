package controller;

import controller.commands.CommandFactory;
import controller.commands.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;


/**
 * The Hospital servlet.
 */
@WebServlet(name = "Controller" , urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    
public static final String MAIN = "/main.jsp";
public static final String INDEX = "/index.jsp";
public static final String DOCTOR = "/jsp/doctor.jsp";
public static final String NURSE = "/jsp/nurse.jsp";
public static final String PATIENT = "/jsp/patient.jsp";
public static final String PATIENT_REGISTRATION = "/jsp/patientReg.jsp";
public static final String ERROR = "/jsp/error.jsp";
public static final String REGISTRATION = "/jsp/registration.jsp";
public static final String MANIPULATIONS = "/jsp/manipulations.jsp";
public static final String APPOINT_MANIPULATION = "/jsp/appointManipulation.jsp";
public static final String PASSWORD_CHANGE = "/jsp/changePassword.jsp";
public static final String RETIREMENT = "/jsp/retirement.jsp";



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String page = null;
        CommandFactory client = new CommandFactory();
        Command command = client.getCommand(request);
        page = command.execute(request);
        
        if (page != null ) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = Controller.ERROR;
            request.setAttribute("errorMessage", "ERROR,The NULL COMMAND");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }    
    }        
        
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
