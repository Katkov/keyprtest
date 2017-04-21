package keyprtest.katkov.com.keyprtest;


import keyprtest.katkov.com.keyprtest.di.ApplicationModule;


/**
 * Created by michaelkatkov on 4/21/17.
 */

public class TestApplication extends KeyprApplication {

    private ApplicationModule mApplicationModule;

    @Override
    ApplicationModule getApplicationModule() {
        if (mApplicationModule == null) {
            return super.getApplicationModule();
        }
        return mApplicationModule;
    }

    public void setApplicationModule(ApplicationModule mApplicationModule) {
        this.mApplicationModule = mApplicationModule;
        initComponent();
    }

}
