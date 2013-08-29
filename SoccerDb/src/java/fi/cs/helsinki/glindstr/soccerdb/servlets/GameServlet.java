package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.models.Game;
import fi.cs.helsinki.glindstr.soccerdb.dao.GameDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.GameDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.dao.MembershipDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.MembershipDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.dao.SeasonDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.SeasonDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.models.League;
import fi.cs.helsinki.glindstr.soccerdb.models.Season;
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
     * location of the update game page
     */
    private static final String UPDATE_GAME = "/updategame.jsp";
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
    private MembershipDao membershipDao;
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
        this.membershipDao = new MembershipDaoImpl();
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
        HttpSession session = request.getSession();
        String redirect;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listGames"))
        {
            redirect = GAME_LIST;
            request.setAttribute("games", gameDao.getAllGames());
        }
        else if (action.equalsIgnoreCase("addgame"))
        {
            redirect = ADD_GAME;
            request.setAttribute("leagues", this.leagueDao.getAllLeagues());
            request.setAttribute("seasons", this.seasonDao.getAllSeasons());
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            redirect = GAME_LIST;
            gameDao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("games", gameDao.getAllGames());
        }
        else if (action.equalsIgnoreCase("addgame1"))
        {
            int leagueId = Integer.parseInt(request.getParameter("leagueId"));
            int seasonId = Integer.parseInt(request.getParameter("seasonId"));
            game.setLeagueId(leagueId);
            game.setSeasonId(seasonId);
            setSessionAttributes(leagueId, seasonId, session);
            redirect = ADD_GAME2;
        }
        else if (action.equalsIgnoreCase("addgame2"))
        {
            if (request.getParameter("homeTeamId").equals(request.getParameter("awayTeamId")))
            {
                redirect = ADD_GAME2;
                request.setAttribute("message", "Home Team and Away Team cannot be the same. Please try again.");
            }
            else
            {
                redirect = GAME_LIST;
                insertNewGameIntoDatabase(request, session);
                request.setAttribute("games", gameDao.getAllGames());
            }
        }
        else if (action.equalsIgnoreCase("update"))
        {
            redirect = UPDATE_GAME;
            int id = Integer.parseInt(request.getParameter("id"));
            game = gameDao.getGameById(id);
            request.setAttribute("game", game);
        }
        else if (action.equalsIgnoreCase("updateScore"))
        {
            redirect = GAME_LIST;
            updateScore(request, session);
            request.setAttribute("games", gameDao.getAllGames());
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
     * Adds teams, league and season attributes to the session.
     *
     * @param leagueId the id of the league that the user has selected
     * @param seasonId the id of the season that the user has selected
     * @param session the current session
     */
    private void setSessionAttributes(int leagueId, int seasonId, HttpSession session)
    {
        League chosenLeague = leagueDao.getById(leagueId);
        Season chosenSeason = seasonDao.getById(seasonId);
        session.setAttribute("teams", membershipDao.getTeamsByLeagueAndSeason(leagueId, seasonId));
        session.setAttribute("league", chosenLeague);
        session.setAttribute("season", chosenSeason);
    }

    /**
     * Inserts a new record into the game table.
     *
     * @param request servlet request
     * @param session the current session
     * @throws NumberFormatException if failing to parse integers
     */
    private void insertNewGameIntoDatabase(HttpServletRequest request, HttpSession session) throws NumberFormatException
    {
        game.setDateFromString(request.getParameter("date"));
        game.setHomeTeamId(Integer.parseInt(request.getParameter("homeTeamId")));
        game.setAwayTeamId(Integer.parseInt(request.getParameter("awayTeamId")));
        int homeScore;
        int awayScore;
        try
        {
            homeScore = Integer.parseInt(request.getParameter("homeScore"));
            awayScore = Integer.parseInt(request.getParameter("awayScore"));
        }
        catch (NumberFormatException e)
        {
            homeScore = -1;
            awayScore = -1;
        }
        game.setHomeScore(homeScore);
        game.setAwayScore(awayScore);
        game.setEditedBy((Integer) session.getAttribute("userId"));
        gameDao.save(game);
    }

    /**
     * Inserts the updated score in the table.
     *
     * @param request servlet request
     * @param session the current session
     */
    private void updateScore(HttpServletRequest request, HttpSession session)
    {
        game.setHomeScore(Integer.parseInt(request.getParameter("homeScore")));
        game.setAwayScore(Integer.parseInt(request.getParameter("awayScore")));
        game.setEditedBy((Integer) session.getAttribute("userId"));
        gameDao.updateScore(game);
    }
}
