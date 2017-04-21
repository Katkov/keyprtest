package keyprtest.katkov.com.keyprtest;


import com.google.android.gms.maps.model.LatLng;

import java.util.Random;


/**
 * Created by michaelkatkov on 4/21/17.
 */

public class LocationUtil {

    public static LatLng getRandomLocation(double x0, double y0, double radius) {
        Random random = new Random();

        // Convert radius from meters to degrees
        double radiusInDegrees = radius / 111000d;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Math.toRadians(y0));

        double lat = new_x + x0;
        double lon = y + y0;
        return new LatLng(lat, lon);
    }

}
