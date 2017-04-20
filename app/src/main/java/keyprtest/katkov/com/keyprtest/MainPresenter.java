package keyprtest.katkov.com.keyprtest;


import javax.inject.Inject;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class MainPresenter implements MainContract.Presenter {

    @Inject
    KeyprManager mKeyprManager;

    private MainContract.View mView;


    public MainPresenter(MainContract.View view) {
        KeyprApplication.getRootComponent().inject(this);
        mView = view;
    }


    @Override
    public void subscribe() {

    }


    @Override
    public void populate() {
        KeyprModel desiredModel = mKeyprManager.getDesiredModel();
        boolean isInGeoFence = mKeyprManager.isInGeoFence();
        mView.populateFields(desiredModel, isInGeoFence);
    }


    @Override
    public void startWatching() {

    }


    @Override
    public void stopWatching() {

    }


    @Override
    public void unSubscribe() {

    }
}
