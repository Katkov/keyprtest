package keyprtest.katkov.com.keyprtest;

/**
 * Created by michaelkatkov on 4/20/17.
 */

public class KeyprModel {

    public String wifiName;
    public Double latitude;
    public Double longitude;
    public Double radius;

    public static KeyprModel create(String wifiName, Double latitude, Double longitude, Double radius) {
        KeyprModel model = new KeyprModel();
        model.wifiName = wifiName;
        model.latitude = latitude;
        model.longitude = longitude;
        model.radius = radius;
        return model;
    }

}
