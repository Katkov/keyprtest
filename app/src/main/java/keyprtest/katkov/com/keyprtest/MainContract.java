package keyprtest.katkov.com.keyprtest;


import android.app.Activity;
import android.content.DialogInterface;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class MainContract {

    public interface View {

        Activity getActivity();
        void showAlert(String message, DialogInterface.OnClickListener okClickListener);
        void showConfirm(String message, DialogInterface.OnClickListener yesClickListener,
                DialogInterface.OnClickListener noClickListener);
        void populateFields(KeyprModel model, boolean isInGeoFence, boolean isStartedWatching);
        KeyprModelValidator.Result readFields();
    }

    public interface Presenter {
        void subscribe();
        void populate();
        void watch();
        void unSubscribe();
    }
}
