package keyprtest.katkov.com.keyprtest;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Bind(R.id.main_wifi_name_et)
    EditText mWifiNameEt;

    @Bind(R.id.main_latitude_et)
    EditText mLatitudeEt;

    @Bind(R.id.main_longitude_et)
    EditText mLongitudeEt;

    @Bind(R.id.main_radius_et)
    EditText mRadiusEt;

    @Bind(R.id.main_notice_tv)
    TextView mNoticeTv;

    @Bind(R.id.main_start_watching_btn)
    Button mStartWatchingBtn;

    private MainPresenter mMainPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.populate();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.subscribe();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mMainPresenter.unSubscribe();
    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showAlert(String message, DialogInterface.OnClickListener okClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert));
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.ok), okClickListener);
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void showConfirm(String message, DialogInterface.OnClickListener yesClickListener,
            DialogInterface.OnClickListener noClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.alert));
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.yes), yesClickListener);
        builder.setNegativeButton(getString(R.string.no), noClickListener);
        builder.setCancelable(false);
        builder.create().show();
    }


    @Override
    public void populateFields(KeyprModel model, boolean isInGeoFence, boolean isStartedWatching) {
        mWifiNameEt.setText(model.wifiName);
        mLatitudeEt.setText(String.valueOf(model.latitude));
        mLongitudeEt.setText(String.valueOf(model.longitude));
        mRadiusEt.setText(String.valueOf(model.radius));
        setUpState(isInGeoFence, isStartedWatching);
    }


    @Override
    public KeyprModelValidator.Result readFields() {
        KeyprModel keyprModel = new KeyprModel();
        keyprModel.wifiName = mWifiNameEt.getText().toString().trim();
        if (!mLatitudeEt.getText().toString().isEmpty()) {
            keyprModel.latitude = Double.parseDouble(mLatitudeEt.getText().toString());
        }
        if (!mLongitudeEt.getText().toString().isEmpty()) {
            keyprModel.longitude = Double.parseDouble(mLongitudeEt.getText().toString());
        }
        if (!mRadiusEt.getText().toString().isEmpty()) {
            keyprModel.radius = Double.parseDouble(mRadiusEt.getText().toString());
        }
        return KeyprModelValidator.validate(this, keyprModel);
    }


    @OnClick(R.id.main_start_watching_btn)
    public void onStartWatching() {
        mMainPresenter.watch();
    }


    private void setUpNotice(boolean isInGeoFence) {
        if (isInGeoFence) {
            mNoticeTv.setText(getString(R.string.you_are_inside_zone));
            mNoticeTv.setBackgroundColor(ContextCompat.getColor(this,R.color.colorGreen));
        } else {
            mNoticeTv.setText(getString(R.string.you_are_outside_zone));
            mNoticeTv.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
        }
    }


    private void setUpState(boolean isInGeoFence, boolean isStartedWatching) {
        if (isStartedWatching) {
            mNoticeTv.setVisibility(View.VISIBLE);
            setUpNotice(isInGeoFence);
            mStartWatchingBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
            mStartWatchingBtn.setText(getString(R.string.stop_watching));
        } else {
            mNoticeTv.setVisibility(View.GONE);
            mStartWatchingBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorGreen));
            mStartWatchingBtn.setText(getString(R.string.start_watching));
        }
    }

}
