package keyprtest.katkov.com.keyprtest;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.main_wifi_name_et)
    EditText mWifiNameEt;

    @BindView(R.id.main_latitude_et)
    EditText mLatitudeEt;

    @BindView(R.id.main_longitude_et)
    EditText mLongitudeEt;

    @BindView(R.id.main_radius_et)
    EditText mRadiusEt;

    @BindView(R.id.main_notice_tv)
    TextView mNoticeTv;

    @BindView(R.id.main_start_watching_btn)
    Button mStartWatchingBtn;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.populate();
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
}
