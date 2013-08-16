
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.models.User;
import fi.cs.helsinki.glindstr.models.UserDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet
{
    /**
     * location of registration page
     */
    private static final String REGISTER = "/register.jsp";
    /**
     * location of login page
     */
    private static final String LOGIN = "/login.jsp";
    /**
     *  data access object for users table 
     */
    private UserDao dao;
    
    /**
     * Class constructor.
     */
    public RegisterServlet()
    {
        super();
        dao = new UserDao();        
    }    

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String redirect;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null)
        {
            User user = new User();
            user.setUsername(username);
            user.setPass(password);
            dao.addUser(user);
            redirect = LOGIN;
        }
        else
        {
            redirect = REGISTER;
        }
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }


}
