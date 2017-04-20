package keyprtest.katkov.com.keyprtest;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class KeyprModelValidator {

    public static class Result {

        public boolean isValid;
        public List<String> errors;
        public KeyprModel model;

        public Result() {
            errors = new ArrayList<>();
        }
    }

    public static Result validate(Context context, @NonNull KeyprModel model) {
        Result result = new Result();
        result.model = model;
        if (!isValidLatitude(model.latitude)) result.errors.add(context.getString(R.string.latitude_error));
        if (!isValidLongitude(model.longitude)) result.errors.add(context.getString(R.string.longitude_error));
        if (!isValidWifiName(model.wifiName)) result.errors.add(context.getString(R.string.wifi_name_error));
        if (!isValidRadius(model.radius)) result.errors.add(context.getString(R.string.radius_error));
        if (result.errors.isEmpty()) result.isValid = true;
        return result;
    }

    private static boolean isValidLatitude(Double latitude) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }

    private static boolean isValidLongitude(Double longitude) {
        return longitude != null && longitude >= - 180 && longitude <= 180;
    }

    private static boolean isValidRadius(Double radius) {
        return radius != null && radius > 0;
    }

    private static boolean isValidWifiName(String wifiName) {
        return wifiName != null && !wifiName.isEmpty();
    }

}
