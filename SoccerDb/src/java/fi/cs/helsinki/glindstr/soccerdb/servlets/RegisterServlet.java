package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.soccerdb.models.User;
import fi.cs.helsinki.glindstr.soccerdb.dao.UserDaoImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the register functionality.
 *
 */
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
     * data access object for users table
     */
    private UserDaoImpl dao;

    /**
     * Class constructor.
     */
    public RegisterServlet()
    {
        super();
        dao = new UserDaoImpl();
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
        User user = createUser(request);
        if (dao.recordExists(user))
        {
            redirect = REGISTER;
            request.setAttribute("message", "Username " + user.getUsername() + " already exists. Please try again.");
        }
        else
        {
           dao.save(user);
           redirect = LOGIN; 
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    /**
     * Creates a new user object based on the input.
     * @param request serlvet request
     * @return a new user
     */
    private User createUser(HttpServletRequest request)
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPass(password);
        return user;
    }
}
