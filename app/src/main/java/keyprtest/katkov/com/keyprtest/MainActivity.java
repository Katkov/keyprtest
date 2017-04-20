package keyprtest.katkov.com.keyprtest;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
