package keyprtest.katkov.com.keyprtest.di;


import dagger.Component;
import keyprtest.katkov.com.keyprtest.*;

import javax.inject.Singleton;


@Singleton
@Component(modules = { ApplicationModule.class })
public interface RootComponent {

    void inject(MainPresenter presenter);

    void inject(KeyprManager manager);

    void inject(KeyprLocationManager manager);

    void inject(KeyprWifiManager manager);

    void inject(WatcherService service);

}
