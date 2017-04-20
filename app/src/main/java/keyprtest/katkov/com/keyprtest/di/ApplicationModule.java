package keyprtest.katkov.com.keyprtest.di;


import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;

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
    public SharedPreferences provideSharedPreferences() {
        return mApplication.getSharedPreferences(mApplication.getApplicationInfo().name, Context.MODE_PRIVATE);
    }


    @Provides
    @Singleton
    NotificationManager provideNotificationManager() {
        return (NotificationManager) mApplication.getSystemService(Context.NOTIFICATION_SERVICE);
    }

}
