package kenigsberg.servlet;

import com.google.gson.Gson;
import kenigsberg.closestcity.ClosestCity;
import kenigsberg.servlet.WorldCitiesServlet;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class WorldCitiesServletTest {

    @Test
    public void doGet() throws ServletException, IOException {
        // given
        final ClosestCity closestCity = mock();
        ArrayList<String> closestCityResult = new ArrayList<>();
        closestCityResult.add("Manhattan");
        closestCityResult.add("40.771724");
        closestCityResult.add("-73.988");
        doReturn(closestCityResult).when(closestCity).getClosestCity(40.771724, -73.988);

        Gson gson = new Gson();

        final WorldCitiesServlet servlet = new WorldCitiesServlet(gson, closestCity);
        HttpServletRequest request = mock();
        doReturn("40.771724").when(request).getParameter("lat");
        doReturn("-73.988").when(request).getParameter("lon");

        HttpServletResponse response = mock();
        PrintWriter out = mock();
        doReturn(out).when(response).getWriter();

        // when
        servlet.doGet(request, response);

        // then
        verify(response).setContentType("text/json");
        verify(out).println(anyString());
    }
}