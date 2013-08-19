
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.dao.SeasonDao;
import fi.cs.helsinki.glindstr.models.Season;
import fi.cs.helsinki.glindstr.dao.SeasonDaoImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the management of seasons.
 * @author Gabriel
 */
public class SeasonServlet extends HttpServlet
{
    /**
     * location of the season table
     */
    private static final String SEASON_LIST = "/seasons.jsp";
    
    /**
     * location of the menu page
     */
    private static final String MENU = "/welcome.jsp";
    
    /**
     * location of the add season page 
     */
    private static final String ADD_SEASON = "/addseason.jsp";
    
    /**
     * data access object for season table
     */    
    private SeasonDao dao;
    
    /**
     * Class constructor.
     */
    public SeasonServlet()
    {
        super();
        this.dao = new SeasonDaoImpl();
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
        if (action.equalsIgnoreCase("listSeasons"))
        {            
            redirect = SEASON_LIST;
            request.setAttribute("seasons", dao.getAllSeasons());
        }
        else if (action.equalsIgnoreCase("addseason"))
        {
            redirect = ADD_SEASON;            
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            redirect = SEASON_LIST;
            dao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("seasons", dao.getAllSeasons());
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
        Season season = new Season();
        season.setName(request.getParameter("name"));
        dao.save(season);
        RequestDispatcher rd = request.getRequestDispatcher(SEASON_LIST);
        request.setAttribute("seasons", dao.getAllSeasons());
        rd.forward(request, response);
    }

  
}
