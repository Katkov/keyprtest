package keyprtest.katkov.com.keyprtest;


import android.text.TextUtils;

import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class MainPresenter implements MainContract.Presenter, Observer {

    @Inject
    KeyprManager mKeyprManager;

    @Inject
    KeyprLocationManager mLocationManager;

    @Inject
    Routing mRouting;

    private MainContract.View mView;


    public MainPresenter(MainContract.View view) {
        KeyprApplication.getRootComponent().inject(this);
        mView = view;
    }


    @Override
    public void subscribe() {
        mKeyprManager.addObserver(this);
    }


    @Override
    public void populate() {
        KeyprModel desiredModel = mKeyprManager.getDesiredModel();
        if (desiredModel != null) {
            boolean isInGeoFence = mKeyprManager.isInGeoFence();
            boolean isStartedWatching = mKeyprManager.isStartedWatching();
            mView.populateFields(desiredModel, isInGeoFence, isStartedWatching);
        }
    }


    @Override
    public void watch() {
        if (mKeyprManager.isStartedWatching()) {
            stopWatching();
        } else {
            startWatching();
        }
    }


    @Override
    public void unSubscribe() {
        mKeyprManager.deleteObserver(this);
    }


    private void startWatching() {
        KeyprModelValidator.Result result = mView.readFields();
        if (!result.isValid) {
            mView.showAlert(TextUtils.join(" , ", result.errors), (dialog, which) -> dialog.dismiss());
        } else {
            mKeyprManager.setDesiredModel(result.model);
            mLocationManager.start(() -> {
                mLocationManager.requestUpdates(mView.getActivity(), KeyprLocationManager.REQUEST_CHECK_SETTINGS);
                mRouting.startWatching(mView.getActivity());
            });
            mKeyprManager.setIsStartedWatching(true);
            populate();
        }
    }


    private void stopWatching() {
        mKeyprManager.setIsStartedWatching(false);
        mRouting.stopWatching(mView.getActivity());
        populate();
    }


    @Override
    public void update(Observable o, Object arg) {
        populate();
    }
}
