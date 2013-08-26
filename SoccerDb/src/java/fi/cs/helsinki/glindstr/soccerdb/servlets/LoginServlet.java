package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.soccerdb.dao.UserDao;
import fi.cs.helsinki.glindstr.soccerdb.models.User;
import fi.cs.helsinki.glindstr.soccerdb.dao.UserDaoImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet handles the login functionality.
 *
 */
public class LoginServlet extends HttpServlet
{

    /**
     * location of front page
     */
    private static final String WELCOME = "/welcome.jsp";
    /**
     * location of login page
     */
    private static final String LOGIN = "/login.jsp";
    /**
     * data access object for users table
     */
    private UserDao dao;

    /**
     * Class constructor.
     */
    public LoginServlet()
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
        String redirect = LOGIN;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null)
        {
            User user = new User();
            user.setUsername(username);
            user.setPass(password);

            if (dao.validateUser(user))
            {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                redirect = WELCOME;
            } 
            else
            {
                request.setAttribute("message", "Unknown username and/or password, try again");
                redirect = LOGIN;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }
}
