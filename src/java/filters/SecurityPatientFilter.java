package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Not allow for doctors and nurses and unregistered users join to the patient page.
 */
@WebFilter(filterName = "SecurityPatientFilter", urlPatterns = {"/jsp/patient.jsp"})
public class SecurityPatientFilter implements Filter {
    
    
    /**
     * filter configuration
     */
    private FilterConfig filterConfig = null;
    
    
    public SecurityPatientFilter() {
    }    
    
    
    /**
     * Not allow users which is not patients join to the patient page.
     * @param request servlet request
     * @param response servlet response
     * @throws IOException
     * @throws ServletException 
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        String user = (String) session.getAttribute("user");
        
        if(user == null || user.equals("DOCTOR") || user.equals("NURSE")) {
            ((HttpServletResponse) response).sendRedirect(
                                    ((HttpServletRequest)request).getContextPath() + 
                                            controller.Controller.MAIN);
        }
    }    

    /**
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        doBeforeProcessing(request, response);
        
        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     * @param filterConfig the input filter config
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityPatientFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityPatientFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
