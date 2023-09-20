package kenigsberg.closestcity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import java.util.List;

import static java.lang.Math.sqrt;

public class ClosestCity {


    public ClosestCity() {
    }

    public ArrayList<String> getClosestCity(double lat1, double lon1) throws IOException {

        ArrayList<String> closetCity = new ArrayList<>();

        File csvData = new File("src/main/resources/worldcities.csv");
        CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
        List<CSVRecord> records = parser.getRecords();

        double lat2 = Double.parseDouble(records.get(1).get(2));
        double lon2 = Double.parseDouble(records.get(1).get(3));

        closetCity.add(records.get(1).get(0));
        closetCity.add(String.valueOf(lat2));
        closetCity.add(String.valueOf(lon2));

        double initialDistance = sqrt(((lat2 - lat1) * (lat2 - lat1)) + ((lon2 - lon1) * (lon2 - lon1)));

        for (int i = 2; i < records.size(); i++)
        {
            lat2 = Double.parseDouble(records.get(i).get(2));
            lon2 = Double.parseDouble(records.get(i).get(3));

            double distance = sqrt(((lat2 - lat1) * (lat2 - lat1)) + ((lon2 - lon1) * (lon2 - lon1)));

            if (distance < initialDistance)
            {
                closetCity.set(0, records.get(i).get(0));
                closetCity.set(1, records.get(i).get(2));
                closetCity.set(2, records.get(i).get(3));
                initialDistance = distance;
            }
        }
        return closetCity;
    }
}
