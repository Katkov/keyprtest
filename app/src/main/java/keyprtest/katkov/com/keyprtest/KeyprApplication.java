package keyprtest.katkov.com.keyprtest;


import android.app.Application;
import keyprtest.katkov.com.keyprtest.di.ApplicationModule;
import keyprtest.katkov.com.keyprtest.di.DaggerRootComponent;
import keyprtest.katkov.com.keyprtest.di.RootComponent;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class KeyprApplication extends Application {

    public static final String TAG = "KeyprApplication";

    private static RootComponent mRootComponent;


    public static RootComponent getRootComponent() {
        return mRootComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }


    void initComponent() {
        ApplicationModule applicationModule = new ApplicationModule(this);
        mRootComponent = DaggerRootComponent.builder().applicationModule(applicationModule).build();
    }

    ApplicationModule getApplicationModule() {
        return new ApplicationModule(this);
    }
}
