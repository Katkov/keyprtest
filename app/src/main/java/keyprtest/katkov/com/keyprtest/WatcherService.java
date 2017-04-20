package keyprtest.katkov.com.keyprtest;


import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WatcherService extends Service {

    private static final String TAG = "WatcherService";

    @Inject
    KeyprLocationManager mLocationManager;

    @Inject
    KeyprManager mKeyprManager;

    @Inject
    KeyprWifiManager mWifiManager;

    private boolean mShouldStop = false;

    private Handler mHandler;


    @Override
    public void onCreate() {
        super.onCreate();
        KeyprApplication.getRootComponent().inject(this);
        mHandler = new Handler();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        runTask();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mShouldStop = true;
        mLocationManager.stop();
    }


    private Runnable task = () -> {
        if(!mShouldStop) {
            updateModel();
            runTask();
        }
    };


    private void runTask() {
        mHandler.postDelayed(task, 1000);
    }


    private void updateModel() {
        Location location = mLocationManager.getLastLocation();
        String wifiName = mWifiManager.getWifiName(this);
        KeyprModel keyprModel = new KeyprModel();
        if (location != null) {
            keyprModel.latitude = location.getLatitude();
            keyprModel.longitude = location.getLongitude();
        }
        keyprModel.wifiName = wifiName;
        mKeyprManager.setCurrentModel(keyprModel);
    }

}
