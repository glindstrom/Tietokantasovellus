
package fi.cs.helsinki.glindstr.soccerdb.servlets;

import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.LeagueDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.models.Membership;
import fi.cs.helsinki.glindstr.soccerdb.dao.MembershipDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.MembershipDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.dao.SeasonDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.SeasonDaoImpl;
import fi.cs.helsinki.glindstr.soccerdb.dao.TeamDao;
import fi.cs.helsinki.glindstr.soccerdb.dao.TeamDaoImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the managing of memberships.
 * @author Gabriel
 */
public class MembershipServlet extends HttpServlet
{
    /**
     * location of the membership table
     */
    private static final String MEMBERSHIP_LIST = "/memberships.jsp";
    
    /**
     * location of the menu page
     */
    private static final String MENU = "/welcome.jsp";
    
    /**
     * location of the add membership page 
     */
    private static final String ADD_MEMBERSHIP = "/addmembership.jsp";
    
    /**
     * data access object for membership table
     */    
    private MembershipDao membershipDao;
    
    private LeagueDao leagueDao;
    
    private TeamDao teamDao;
    
    private SeasonDao seasonDao;
    
    /**
     * Class constructor.
     */
    public MembershipServlet()
    {
        super();
        this.membershipDao = new MembershipDaoImpl();
        this.leagueDao = new LeagueDaoImpl();
        this.teamDao = new TeamDaoImpl();
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
        String redirect;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listMemberships"))
        {            
            redirect = MEMBERSHIP_LIST;
            request.setAttribute("memberships", membershipDao.getAllMemberships());
        }
        else if (action.equalsIgnoreCase("addmembership"))
        {
            redirect = ADD_MEMBERSHIP;
            request.setAttribute("leagues", this.leagueDao.getAllLeagues());
            request.setAttribute("teams", this.teamDao.getAllTeams());
            request.setAttribute("seasons", this.seasonDao.getAllSeasons());
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            redirect = MEMBERSHIP_LIST;
            membershipDao.delete(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("memberships", membershipDao.getAllMemberships());
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
        Membership membership = new Membership();
        membership.setLeagueId(Integer.parseInt(request.getParameter("leagueId")));
        membership.setSeasonId(Integer.parseInt(request.getParameter("seasonId")));
        membership.setTeamId(Integer.parseInt(request.getParameter("teamId")));
        membershipDao.save(membership);
        RequestDispatcher rd = request.getRequestDispatcher(MEMBERSHIP_LIST);
        request.setAttribute("memberships", membershipDao.getAllMemberships());
        rd.forward(request, response);
    }

  
}
