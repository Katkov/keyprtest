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
    }

    public interface Presenter {

        void populate();
        void startWatching();
        void stopWatching();
    }
}
