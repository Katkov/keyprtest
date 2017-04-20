package keyprtest.katkov.com.keyprtest;


import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.*;

import javax.inject.Inject;


/**
 * Created by michaelkatkov on 04/19/17.
 */
public class KeyprLocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    public static final  int    REQUEST_CHECK_SETTINGS = 0x1;
    private static final String TAG                    = "KeyprLocationManager";
    private static final long   SEC                    = 1000;
    private static final long   TEN_SEC                = 10 * 1000;

    @Inject
    Context mContext;

    @Inject
    KeyprManager mKeyprManager;

    @Inject
    LocationManager mLocationManager;

    private Location mLocation;

    private GoogleApiClient mGoogleApiClient;

    private Callback mCallback;


    public interface Callback {

        void onCall();
    }


    public KeyprLocationManager() {
        KeyprApplication.getRootComponent().inject(this);
        buildGoogleApiClient();
    }


    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }


    private LocationRequest buildLocationRequest(final Activity activity, final int requestCode) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(TEN_SEC);
        locationRequest.setFastestInterval(SEC);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(r -> {
            final Status status = r.getStatus();
            switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(activity, requestCode);
                } catch (IntentSender.SendIntentException e) {
                    Log.d(TAG, e.getMessage());
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
            }
        });
        return locationRequest;
    }


    public void requestUpdates(Activity activity, int requestCode) {
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mGoogleApiClient, buildLocationRequest(activity, requestCode), this);
    }


    public void start(Callback callback) {
        mCallback = callback;
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        } else {
            mCallback.onCall();
        }
    }


    public void stop() {
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }


    public Location getLastLocation() {
        if (mLocation == null) {
            return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        return mLocation;
    }


    public boolean isGPSEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    @Override
    public void onConnected(Bundle bundle) {
        if (mCallback != null) {
            mCallback.onCall();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mGoogleApiClient.connect();

    }


    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
    }

}
