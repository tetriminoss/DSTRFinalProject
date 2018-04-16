package PrintPackage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {

    LocationDAO locationDAO;
    MarketingAgentDAO marketingAgentDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        locationDAO = new LocationDAO(jdbcURL, jdbcUsername, jdbcPassword);
        marketingAgentDAO = new MarketingAgentDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewLocationForm(request, response);
                    break;
                case "/insert":
                    insertLocationRecord(request, response);
                    break;
                case "/delete":
                    deleteLocation(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateLocation(request, response);
                    break;
                case "/newAgent":
                    showNewMarketingAgentForm(request, response);
                    break;
                case "/insertAgent":
                    insertMarketingAgentRecord(request, response);
                    break;
                case "/deleteAgent":
                    deleteMarketingAgent(request, response);
                    break;
                case "/editAgent":
                    showMarketingAgentEditForm(request, response);
                    break;
                case "/updateAgent":
                    updateMarketingAgent(request, response);
                    break;
                case "/list":
                    listAllLocations(request, response);
                case "/listAgents":
                    listAllMarketingAgents(request, response);
                    break;
                default:
                    response.sendRedirect("admin.jsp");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAllLocations(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Location> listLocation = locationDAO.listAllLocations();
        request.setAttribute("listLocation", listLocation);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationList.jsp");
        dispatcher.forward(request, response);
    }

    private void listAllMarketingAgents(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<MarketingAgent> listMarketingAgent = marketingAgentDAO.listAllMarketingAgents();
        request.setAttribute("listMarketingAgent", listMarketingAgent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("marketingAgentList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewLocationForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("locationForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewMarketingAgentForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("marketingAgentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLocationRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String locationName = request.getParameter("locationName");
        int distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
        Location locationObj = new Location(locationName, distributionCapacity);
        locationDAO.insertLocationRecord(locationObj);
        response.sendRedirect("list");
    }

    private void insertMarketingAgentRecord(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        MarketingAgent marketingAgentObj = new MarketingAgent(firstName, lastName, phoneNo, email);
        marketingAgentDAO.insertMarketingAgentRecord(marketingAgentObj);
        response.sendRedirect("listAgents");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Location existinglocation = locationDAO.getLocation(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("locationForm.jsp");
        request.setAttribute("location", existinglocation);
        dispatcher.forward(request, response);
    }

    private void showMarketingAgentEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        MarketingAgent existingMArketingAgent = marketingAgentDAO.getMarketingAgent(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("marketingAgentForm.jsp");
        request.setAttribute("marketingAgent", existingMArketingAgent);
        dispatcher.forward(request, response);
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String locationName = request.getParameter("locationName");
        int distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
        Location location = new Location(id, locationName, distributionCapacity);
        locationDAO.updateLocation(location);
        response.sendRedirect("list");
    }

    private void updateMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        MarketingAgent marketingAgentObj = new MarketingAgent(id, firstName, lastName, phoneNo, email);
        marketingAgentDAO.updateMarketingAgent(marketingAgentObj);
        response.sendRedirect("listAgents");
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Location location = new Location(id);
        locationDAO.deleteLocation(location);
        response.sendRedirect("list");
    }

    private void deleteMarketingAgent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        MarketingAgent marketAgentObj = new MarketingAgent(id);
        marketingAgentDAO.deleteMarketingAgent(marketAgentObj);
        response.sendRedirect("listAgents");
    }
}
