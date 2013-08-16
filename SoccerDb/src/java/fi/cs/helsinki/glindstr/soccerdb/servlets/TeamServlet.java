
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.dao.TeamDao;
import fi.cs.helsinki.glindstr.models.Team;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles adding and deleting teams.
 * @author Gabriel
 */
public class TeamServlet extends HttpServlet
{
    /**
     * location of the league table
     */
    private final String TEAM_LIST = "/teams.jsp";
    
    /**
     * location of the menu page
     */
    private final String MENU = "/welcome.jsp";
    
    /**
     * location of the add league page 
     */
    private final String ADD_TEAM = "/addteam.jsp";
    
    /**
     * data access object for league table
     */    
    private TeamDao dao;
    
    /**
     * Class constructor.
     */
    public TeamServlet()
    {
        super();
        this.dao = new TeamDao();
    }
  
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String redirect;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listLeagues"))
        {            
            redirect = TEAM_LIST;
            request.setAttribute("teams", dao.getAllTeams());
        }
        else if (action.equalsIgnoreCase("addleague"))
        {
            redirect = ADD_TEAM;            
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            redirect = TEAM_LIST;
            dao.deleteTeam(request.getParameter("id"));
            request.setAttribute("teams", dao.getAllTeams());
        }
        else
        {
            redirect = MENU;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
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
        Team team = new Team();
        team.setName(request.getParameter("name"));
        dao.addTeam(team);
        RequestDispatcher rd = request.getRequestDispatcher(TEAM_LIST);
        request.setAttribute("teams", dao.getAllTeams());
        rd.forward(request, response);
    }

  
}
