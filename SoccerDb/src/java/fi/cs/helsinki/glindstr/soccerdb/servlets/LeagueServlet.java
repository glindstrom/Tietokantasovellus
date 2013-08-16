
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.models.League;
import fi.cs.helsinki.glindstr.models.LeagueDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles adding and editing leagues
 * @author Gabriel
 */
public class LeagueServlet extends HttpServlet
{
    /**
     * location of the league table
     */
    private final String LEAGUE_LIST = "/leagues.jsp";
    
    /**
     * location of the menu page
     */
    private final String MENU = "/welcome.jsp";
    
    /**
     * location of the add league page 
     */
    private final String ADD_LEAGUE = "/addleague.jsp";
    
    /**
     * data access object for league table
     */    
    private LeagueDao dao;
    
    /**
     * Class constructor.
     */
    public LeagueServlet()
    {
        super();
        this.dao = new LeagueDao();
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
        if (action.equalsIgnoreCase("league"))
        {            
            redirect = LEAGUE_LIST;
            request.setAttribute("leagues", dao.getAllLeagues());
        }
        else if (action.equalsIgnoreCase("addleague"))
        {
            redirect = ADD_LEAGUE;            
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            redirect = LEAGUE_LIST;
            dao.deleteLeague(request.getParameter("id"));
            request.setAttribute("leagues", dao.getAllLeagues());
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
        League league = new League();
        league.setName(request.getParameter("name"));
        dao.addLeague(league);
        RequestDispatcher rd = request.getRequestDispatcher(LEAGUE_LIST);
        request.setAttribute("leagues", dao.getAllLeagues());
        rd.forward(request, response);
    }

  
}
