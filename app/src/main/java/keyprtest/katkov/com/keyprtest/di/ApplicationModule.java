package keyprtest.katkov.com.keyprtest.di;


import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import dagger.Module;
import dagger.Provides;
import keyprtest.katkov.com.keyprtest.*;

import javax.inject.Singleton;


/**
 * Created by michaelkatkov on 8/4/16.
 */
@Module
public class ApplicationModule {

    private Application mApplication;


    public ApplicationModule(Application application) {
        mApplication = application;
    }


    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return mApplication.getSharedPreferences(mApplication.getApplicationInfo().name, Context.MODE_PRIVATE);
    }


    @Provides
    @Singleton
    NotificationManager provideNotificationManager() {
        return (NotificationManager) mApplication.getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) mApplication.getSystemService(Context.LOCATION_SERVICE);
    }


    @Provides
    @Singleton
    WifiManager provideWifiManager() {
        return (WifiManager) mApplication.getSystemService(Context.WIFI_SERVICE);
    }


    @Provides
    @Singleton
    KeyprManager provideKeyprManager() {
        return new KeyprManager();
    }


    @Provides
    @Singleton
    KeyprLocationManager provideKeyprLocationManager() {
        return new KeyprLocationManager();
    }


    @Provides
    @Singleton
    KeyprWifiManager provideKeyprWifiManager() {
        return new KeyprWifiManager();
    }


    @Provides
    @Singleton
    Routing provideRouting() {
        return new Routing();
    }

    @Provides
    @Singleton
    PermissionsManager providePermissionsManager() {
        return new PermissionsManager();
    }

}
