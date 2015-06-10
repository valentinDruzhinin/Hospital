package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *  The filter which encode request into utf-8 format. 
 */
public class EncodingFilter implements Filter {
    
    /**
     * The configuration of filter, which contain the encoding of filter.
     */
    private FilterConfig filterConfig = null;
    
    
    public EncodingFilter() {
    }    
    
    
    /**
     * Convert input browser encoding to utf-8 encoding.
     * @param request input servlet request
     * @param response input servlet response
     * @throws IOException
     * @throws ServletException 
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
        String requestEncoding = request.getCharacterEncoding();
        String code = this.filterConfig.getInitParameter("encoding");
        if( code != null
                && !code.equalsIgnoreCase(requestEncoding)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        
    }

    /**
     * Convert request and response to set encoding.
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
        
        doAfterProcessing(request, response);

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
        this.filterConfig = null;
    }

    /**
     * Init method for this filter
     * @param filterConfig
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
            return ("encodingFilter()");
        }
        StringBuffer sb = new StringBuffer("encodingFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
}
