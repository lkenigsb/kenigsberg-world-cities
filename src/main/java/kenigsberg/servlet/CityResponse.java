package kenigsberg.servlet;

/**
 * Json response from kenigsberg.servlet.WorldCitiesServlet
 */
public record CityResponse(String cityName, double lat, double lng) {

}