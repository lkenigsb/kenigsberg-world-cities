package kenigsberg.servlet;

/**
 * Json response from WorldCitiesServlet
 */
public record CityResponse(String cityName, double lat, double lng) {

}