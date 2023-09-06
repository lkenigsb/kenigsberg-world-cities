package kenigsberg.servlet;

import com.google.gson.Gson;
import kenigsberg.worldcities.ClosestCity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that find the closest city in the worldcities.csv and returns it as a json response
 */
public class WorldCitiesServlet extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp
    ) throws ServletException, IOException {
        double lat = Double.parseDouble(req.getParameter("lat"));
        double lon = Double.parseDouble(req.getParameter("lon"));
        ClosestCity closestCity = new ClosestCity(lat, lon);
        String city = closestCity.getClosestCity();

        CityResponse cityResponse = new CityResponse(city, lat, lon);
        resp.setContentType("text/json");
        resp.getWriter().println(gson.toJson(cityResponse));
    }
}