/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.dao.LeagueDao;
import fi.cs.helsinki.glindstr.dao.LeagueDaoImpl;
import fi.cs.helsinki.glindstr.dao.SeasonDao;
import fi.cs.helsinki.glindstr.dao.SeasonDaoImpl;
import fi.cs.helsinki.glindstr.models.League;
import fi.cs.helsinki.glindstr.models.Season;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the standings view.
 *
 */
public class StandingsServlet extends HttpServlet
{
    private static final String STANDINGS = "/standings.jsp";
    private static final String VIEW_STANDINGS = "viewstandings.jsp";
    
    /**
     * data access object for league table
     */
    private LeagueDao leagueDao;
    /**
     * data access object for the season table
     */
    private SeasonDao seasonDao;

    /**
     * Class constructor.
     */
    public StandingsServlet()
    {
        super();
        this.leagueDao = new LeagueDaoImpl();
        this.seasonDao = new SeasonDaoImpl();
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
        request.setAttribute("leagues", this.leagueDao.getAllLeagues());
        request.setAttribute("seasons", this.seasonDao.getAllSeasons());
        RequestDispatcher rd = request.getRequestDispatcher(STANDINGS);
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
        setLeagueAndSeasonAttributes(request);
        RequestDispatcher rd = request.getRequestDispatcher(VIEW_STANDINGS);
        rd.forward(request, response);
    }

    private void setLeagueAndSeasonAttributes(HttpServletRequest request)
    {
        int leagueId = Integer.parseInt(request.getParameter("leagueId"));
        int seasonId = Integer.parseInt(request.getParameter("seasonId"));
        League chosenLeague = leagueDao.getById(leagueId);
        Season chosenSeason = seasonDao.getById(seasonId);
        request.setAttribute("league", chosenLeague);
        request.setAttribute("season", chosenSeason);
    }
}
