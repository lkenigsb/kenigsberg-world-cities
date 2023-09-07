package kenigsberg.servlet;

import com.google.gson.Gson;
import kenigsberg.worldcities.ClosestCity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet that find the closest city in the worldcities.csv and returns it as a json response
 */
public class WorldCitiesServlet extends HttpServlet {

    ClosestCity closestCity;
    private Gson gson;

    /**
     * This is used by Jetty
     */
    public WorldCitiesServlet()
    {
        this(new Gson(), new ClosestCity());
    }

    /**
     * This is used in tests
     *
     * @param gson - to be the Json file to return ot the user
     * @param closestCity - to calculate the closest city of the parem coordinates
     *
     */
    public WorldCitiesServlet(Gson gson, ClosestCity closestCity)
    {
        this.gson = gson;
        this.closestCity = new ClosestCity();
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        String lat = (req.getParameter("lat"));
        String lon = (req.getParameter("lon"));
        ArrayList<String> city = this.closestCity.getClosestCity(Double.parseDouble(lat), Double.parseDouble(lon));

        CityResponse cityResponse = new CityResponse(
                city.get(0), Double.parseDouble(city.get(1)), Double.parseDouble(city.get(2)));
        resp.setContentType("text/json");
        resp.getWriter().println(gson.toJson(cityResponse));
    }
}
