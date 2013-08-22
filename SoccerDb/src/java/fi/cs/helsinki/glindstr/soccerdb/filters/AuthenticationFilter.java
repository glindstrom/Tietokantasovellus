package fi.cs.helsinki.glindstr.soccerdb.filters;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This filter checks that the user is logged in before accessing a page.
 * 
 */
public class AuthenticationFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httprequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;        
        HttpSession session = httprequest.getSession();
        String path = httprequest.getServletPath();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null && !path.equals("/login.jsp"))
        {
            httpresponse.sendRedirect("login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }
}
