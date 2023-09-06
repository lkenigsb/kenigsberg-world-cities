package kenigsberg.worldcities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClosestCityTest {
    @Test
    public void getClosesetCity() throws IOException {
        // given
        double lat = 35.6897;
        double lon = 139.6922;
        ClosestCity closestCity = new ClosestCity(lat, lon);

        // when
        String cityName = closestCity.getClosestCity();

        // then
        Assertions.assertEquals(" Tokyo", cityName);
    }
}