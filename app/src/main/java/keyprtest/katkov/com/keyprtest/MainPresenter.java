package keyprtest.katkov.com.keyprtest;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;


    public MainPresenter(MainContract.View view) {
        mView = view;
    }


    @Override
    public void populate() {

    }


    @Override
    public void startWatching() {

    }


    @Override
    public void stopWatching() {

    }
}
