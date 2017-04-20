package keyprtest.katkov.com.keyprtest.di;


import dagger.Component;
import keyprtest.katkov.com.keyprtest.MainPresenter;

import javax.inject.Singleton;


@Singleton
@Component(modules = { ApplicationModule.class })
public interface RootComponent {

    //Presenters
    void inject(MainPresenter presenter);

}
