
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDao;
import fi.cs.helsinki.glindstr.soccerdb.models.League;
import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDaoImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the management of leagues.
 * 
 */
public class LeagueServlet extends HttpServlet
{
    /**
     * location of the league table
     */
    private static final String LEAGUE_LIST = "/leagues.jsp";
    
    /**
     * location of the menu page
     */
    private static final String MENU = "/welcome.jsp";
    
    /**
     * location of the add league page 
     */
    private static final String ADD_LEAGUE = "/addleague.jsp";
    
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
        this.dao = new LeagueDaoImpl();
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
            dao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("leagues", dao.getAllLeagues());
        }
        else if (action.equalsIgnoreCase("add"))
        {
            redirect = LEAGUE_LIST;
            insertNewLeagueIntoDatabase(request);
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
        doGet(request, response);
    }

    /**
     * Inserts a new league record into the database.
     * @param request serlvet request
     */
    private void insertNewLeagueIntoDatabase(HttpServletRequest request)
    {
        League league = new League();
        league.setName(request.getParameter("name"));
        dao.save(league);
    }

  
}
