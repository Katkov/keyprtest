package keyprtest.katkov.com.keyprtest;


import android.content.Context;
import android.content.Intent;


/**
 * Created by michaelkatkov on 4/20/17.
 */

public class Routing {

    public void startWatching(Context context) {
        Intent intent = new Intent(context, WatcherService.class);
        context.startService(intent);
    }


    public void stopWatching(Context context) {
        Intent intent = new Intent(context, WatcherService.class);
        context.stopService(intent);
    }

}
