package keyprtest.katkov.com.keyprtest;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * Created by michaelkatkov on 3/3/16.
 */
public class PermissionsManager {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    public static final String CAMERA                 = Manifest.permission.CAMERA;
    public static final String GET_ACCOUNTS           = Manifest.permission.GET_ACCOUNTS;
    public static final String COARSE_LOCATION        = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String FINE_LOCATION          = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String READ_PHONE_STATE       = Manifest.permission.READ_PHONE_STATE;
    public static final String CALL_PHONE             = Manifest.permission.CALL_PHONE;
    public static final String RECEIVE_SMS            = Manifest.permission.RECEIVE_SMS;
    public static final String READ_SMS               = Manifest.permission.READ_SMS;
    public static final String SEND_SMS               = Manifest.permission.SEND_SMS;
    public static final String READ_EXTERNAL_STORAGE  = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ_CONTACTS          = Manifest.permission.READ_CONTACTS;

    private static final String[] PERMISSIONS = new String[] { CAMERA, GET_ACCOUNTS, COARSE_LOCATION, FINE_LOCATION,
            READ_PHONE_STATE, CALL_PHONE, RECEIVE_SMS, READ_SMS, SEND_SMS, READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE, READ_CONTACTS
    };


    public void checkAndRequestPermission(Activity activity, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission(activity, permission)) {
            activity.requestPermissions(new String[] { permission }, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }


    public boolean checkPermission(Activity activity, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public void checkAndRequestPermissions(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermissions(activity, permissions)) {
            activity.requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }


    public boolean checkPermissions(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for(String permission: permissions) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean areAllPermissionsGranted(int requestCode, int[] grantResults) {
        switch (requestCode) {
        case REQUEST_CODE_ASK_PERMISSIONS:
            if (areAllPermissionsGranted(grantResults)) {
                return true;
            }
        }
        return false;
    }


    private boolean areAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
