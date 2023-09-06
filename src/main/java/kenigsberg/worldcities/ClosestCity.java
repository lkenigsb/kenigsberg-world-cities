package kenigsberg.worldcities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.List;

import static java.lang.Math.sqrt;

public class ClosestCity {

    ArrayList<Double> coordinates = new ArrayList<>();

    public ClosestCity(double lat, double lon)
    {
        coordinates.add(lat);
        coordinates.add(lon);
    }

    public String getClosestCity() throws IOException {

        double lat1 = coordinates.get(0);
        double lon1 = coordinates.get(1);

        File csvData = new File("src/main/resources/worldcities.csv");
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
        List citiesCoordinateList = parser.stream().toList();

        String[] individualCityCoodinates = citiesCoordinateList.get(1).toString().split(","); //excluding column names
        double lat2 = Double.parseDouble(individualCityCoodinates[4]);
        double lon2 = Double.parseDouble(individualCityCoodinates[5]);
        String closestCity = individualCityCoodinates[3];

        double initialDistance = sqrt(((lat2 - lat1) * (lat2 - lat1)) + ((lon2 - lon1) * (lon2 - lon1)));

        for (int i = 2; i < citiesCoordinateList.size(); i++)
        {
            individualCityCoodinates = citiesCoordinateList.get(i).toString().split(",");
            lat2 = Double.parseDouble(individualCityCoodinates[4]);
            lon2 = Double.parseDouble(individualCityCoodinates[5]);

            double distance = sqrt(((lat2 - lat1) * (lat2 - lat1)) + ((lon2 - lon1) * (lon2 - lon1)));

            if (distance < initialDistance)
            {
                closestCity = individualCityCoodinates[3];
                initialDistance = distance;
            }
        }
        return closestCity;
    }
}
