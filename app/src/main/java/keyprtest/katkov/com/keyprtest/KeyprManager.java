package keyprtest.katkov.com.keyprtest;


import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.Gson;

import javax.inject.Inject;
import java.util.Observable;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class KeyprManager extends Observable {

    private static String DESIRED_MODEL       = "desired_model";
    private static String CURRENT_MODEL       = "current_model";
    private static String IS_STARTED_WATCHING = "is_started_watching";

    @Inject
    SharedPreferences mPreferences;


    public KeyprManager() {
        KeyprApplication.getRootComponent().inject(this);
    }


    public void setIsStartedWatching(boolean isStartedWatching) {
        mPreferences.edit().putBoolean(IS_STARTED_WATCHING, isStartedWatching).apply();
    }


    public boolean isStartedWatching() {
        return mPreferences.getBoolean(IS_STARTED_WATCHING, false);
    }


    public void setDesiredModel(@NonNull KeyprModel model) {
        String jsonString = new Gson().toJson(model);
        mPreferences.edit().putString(DESIRED_MODEL, jsonString).apply();
    }


    @Nullable
    public KeyprModel getDesiredModel() {
        String jsonString = mPreferences.getString(DESIRED_MODEL, null);
        return jsonString != null ? new Gson().fromJson(jsonString, KeyprModel.class) : null;
    }


    public void setCurrentModel(@NonNull KeyprModel model) {
        String jsonString = new Gson().toJson(model);
        mPreferences.edit().putString(CURRENT_MODEL, jsonString).apply();
        if (isStartedWatching()) {
            setChanged();
            notifyObservers();
            clearChanged();
        }
    }


    @Nullable
    public KeyprModel getCurrentModel() {
        String jsonString = mPreferences.getString(CURRENT_MODEL, null);
        return jsonString != null ? new Gson().fromJson(jsonString, KeyprModel.class) : null;
    }


    public boolean isInGeoFence() {
        KeyprModel desiredModel = getDesiredModel();
        KeyprModel currentModel = getCurrentModel();
        if (desiredModel == null || currentModel == null)
            return false;
        return areWifiNamesMatches(desiredModel, currentModel) || isCurrentPointInLocation(desiredModel, currentModel);
    }


    private boolean areWifiNamesMatches(KeyprModel desiredModel, KeyprModel currentModel) {
        return currentModel.wifiName != null && desiredModel.wifiName != null && currentModel.wifiName
                .equals(desiredModel.wifiName);

    }


    private boolean isCurrentPointInLocation(KeyprModel desiredModel, KeyprModel currentModel) {
        return desiredModel.radius != null && desiredModel.longitude != null && desiredModel.latitude != null
                && currentModel.latitude != null && currentModel.longitude != null
                && getDistanceInMeters(desiredModel, currentModel) < desiredModel.radius;
    }


    private double getDistanceInMeters(KeyprModel desiredModel, KeyprModel currentModel) {
        float[] results = new float[1];
        Location.distanceBetween(desiredModel.latitude, desiredModel.longitude, currentModel.latitude,
                currentModel.longitude, results);
        return results[0];
    }
}
