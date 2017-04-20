package keyprtest.katkov.com.keyprtest;


import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import javax.inject.Inject;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class KeyprWifiManager {

    @Inject
    WifiManager mWifiManager;

    public KeyprWifiManager() {
        KeyprApplication.getRootComponent().inject(this);
    }

    public String getWifiName(Context context) {
        if (mWifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return removeQuotationsInCurrentSSIDForJellyBean(wifiInfo.getSSID());
                }
            }
        }
        return null;
    }

    public String removeQuotationsInCurrentSSIDForJellyBean(String ssid){
        int deviceVersion= Build.VERSION.SDK_INT;

        if (deviceVersion >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            if (ssid.startsWith("\"") && ssid.endsWith("\"")){
                ssid = ssid.substring(1, ssid.length()-1);
            }
        }

        return ssid;

    }
}
