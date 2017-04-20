package keyprtest.katkov.com.keyprtest;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import javax.inject.Inject;
import java.util.Observable;
import java.util.Observer;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class MainPresenter implements MainContract.Presenter, Observer {

    private static final String[] PERMISSIONS_GROUP = new String[] { PermissionsManager.COARSE_LOCATION,
            PermissionsManager.FINE_LOCATION
    };

    @Inject
    KeyprManager mKeyprManager;

    @Inject
    KeyprLocationManager mLocationManager;

    @Inject
    PermissionsManager mPermissionsManager;

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
            prepareToStartWatching();
        }
    }


    @Override
    public void unSubscribe() {
        mKeyprManager.deleteObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        populate();
    }


    @Override
    public void checkIfPermissionsGrantedThenStartWatching(int requestCode, @NonNull int[] grantResults) {
        if (mPermissionsManager.areAllPermissionsGranted(requestCode, grantResults)) {
            prepareToStartWatching();
        } else {
            Toast.makeText(mView.getActivity(), "Cancelling, required permissions are not granted", Toast.LENGTH_LONG)
                    .show();
        }
    }


    private void prepareToStartWatching() {
        KeyprModelValidator.Result result = mView.readFields();
        if (!result.isValid) {
            mView.showAlert(TextUtils.join(" , ", result.errors), (dialog, which) -> dialog.dismiss());
        } else {
            if (mPermissionsManager.checkPermissions(mView.getActivity(), PERMISSIONS_GROUP)) {
                startWatching(result);
            } else {
                mPermissionsManager.checkAndRequestPermissions(mView.getActivity(), PERMISSIONS_GROUP);
            }
        }
    }


    private void startWatching(KeyprModelValidator.Result result) {
        mKeyprManager.setDesiredModel(result.model);
        mLocationManager.start(() -> {
            mLocationManager.requestUpdates(mView.getActivity(), KeyprLocationManager.REQUEST_CHECK_SETTINGS);
            mRouting.startWatching(mView.getActivity());
        });
        mKeyprManager.setIsStartedWatching(true);
        populate();
    }


    private void stopWatching() {
        mKeyprManager.setIsStartedWatching(false);
        mRouting.stopWatching(mView.getActivity());
        populate();
    }

}
