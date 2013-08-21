package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.dao.LeagueDao;
import fi.cs.helsinki.glindstr.dao.LeagueDaoImpl;
import fi.cs.helsinki.glindstr.models.Game;
import fi.cs.helsinki.glindstr.dao.GameDao;
import fi.cs.helsinki.glindstr.dao.GameDaoImpl;
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
import javax.servlet.http.HttpSession;

/**
 * This servlet handles the managing of games.
 *
 * @author Gabriel
 */
public class GameServlet extends HttpServlet
{

    /**
     * location of the game table
     */
    private static final String GAME_LIST = "/games.jsp";
    /**
     * location of the menu page
     */
    private static final String MENU = "/welcome.jsp";
    /**
     * location of the add game page
     */
    private static final String ADD_GAME = "/addgame.jsp";
    /**
     * location of the second step for the add game function
     */
    private static final String ADD_GAME2 = "/addgame2.jsp";
    /**
     * data access object for game table
     */
    private GameDao gameDao;
    /**
     * data access object for league table
     */
    private LeagueDao leagueDao;
    /**
     * data access object for the season table
     */
    private SeasonDao seasonDao;
    /**
     * the game being created
     */
    private Game game;

    /**
     * Class constructor.
     */
    public GameServlet()
    {
        super();
        this.gameDao = new GameDaoImpl();
        this.leagueDao = new LeagueDaoImpl();
        this.seasonDao = new SeasonDaoImpl();
        this.game = new Game();
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
        if (action.equalsIgnoreCase("listGames"))
        {
            redirect = GAME_LIST;
            request.setAttribute("games", gameDao.getAllGames());
        } else if (action.equalsIgnoreCase("addgame"))
        {
            redirect = ADD_GAME;
            request.setAttribute("leagues", this.leagueDao.getAllLeagues());
            request.setAttribute("seasons", this.seasonDao.getAllSeasons());
        } else if (action.equalsIgnoreCase("delete"))
        {
            redirect = GAME_LIST;
            gameDao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("games", gameDao.getAllGames());
        } else
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
        HttpSession session = request.getSession();
        String redirect;
        String action = request.getParameter("step");
        if (action != null)
        {
            int leagueId = Integer.parseInt(request.getParameter("leagueId"));
            int seasonId = Integer.parseInt(request.getParameter("seasonId"));
            game.setLeagueId(leagueId);
            game.setSeasonId(seasonId);
            setSessionAttributes( leagueId, seasonId, session);
            redirect = ADD_GAME2;
        } else
        {
            redirect = GAME_LIST;
            game.setDateFromString(request.getParameter("date"));
            game.setHomeTeamId(Integer.parseInt(request.getParameter("homeTeamId")));
            game.setAwayTeamId(Integer.parseInt(request.getParameter("awayTeamId")));
            game.setHomeScore(Integer.parseInt(request.getParameter("homeScore")));
            game.setAwayScore(Integer.parseInt(request.getParameter("awayScore")));
            game.setEditedBy((Integer) session.getAttribute("userId"));
            gameDao.save(game);
            request.setAttribute("games", gameDao.getAllGames());
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    /**
     * Adds teams, league and season attributes to the session.
     * @param leagueId the id of the league that the user has selected
     * @param seasonId the id of the season that the user has selected
     * @param session the current session
     */
    private void setSessionAttributes(int leagueId, int seasonId, HttpSession session)
    {
        League chosenLeague = leagueDao.getById(leagueId);
        Season chosenSeason = seasonDao.getById(seasonId);
        session.setAttribute("teams", gameDao.getTeamsByLeagueAndSeason(leagueId, seasonId));
        session.setAttribute("league", chosenLeague);
        session.setAttribute("season", chosenSeason);
    }
}
