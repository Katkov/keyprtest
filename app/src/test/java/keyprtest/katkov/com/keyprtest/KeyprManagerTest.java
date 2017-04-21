package keyprtest.katkov.com.keyprtest;


import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import keyprtest.katkov.com.keyprtest.di.ApplicationModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Random;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * Created by michaelkatkov on 4/21/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE,
        application = TestApplication.class)
public class KeyprManagerTest {

    @Before
    public void setup(){
        TestApplication app = (TestApplication) RuntimeEnvironment.application;
        ApplicationModule module = new ApplicationModule(app);
        app.setApplicationModule(module);
    }

    @Test
    public void isInGeoFenceTest1() {
        KeyprManager keyprManager = new KeyprManager();
        KeyprModel desired = new KeyprModel();
        desired.wifiName = "Name";
        keyprManager.setDesiredModel(desired);
        KeyprModel current = new KeyprModel();
        current.wifiName = "Name";
        keyprManager.setCurrentModel(current);
        assertTrue(keyprManager.isInGeoFence());
    }

    @Test
    public void isInGeoFenceTest2() {
        KeyprManager keyprManager = new KeyprManager();
        KeyprModel desired = new KeyprModel();
        desired.wifiName = "Name1";
        keyprManager.setDesiredModel(desired);
        KeyprModel current = new KeyprModel();
        current.wifiName = "Name2";
        keyprManager.setCurrentModel(current);
        assertFalse(keyprManager.isInGeoFence());
    }

    @Test
    public void isInGeoFenceTest3() {
        KeyprManager keyprManager = new KeyprManager();
        KeyprModel desired = new KeyprModel();
        desired.latitude = 20.05;
        desired.longitude = 0.15;
        desired.radius = 500.0;
        keyprManager.setDesiredModel(desired);
        KeyprModel current = new KeyprModel();
        LatLng latLng = LocationUtil.getRandomLocation(desired.latitude, desired.longitude, desired.radius - 5);
        current.latitude = latLng.latitude;
        current.longitude = latLng.longitude;
        keyprManager.setCurrentModel(current);
        assertTrue(keyprManager.isInGeoFence());
    }

}
