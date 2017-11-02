package android.cuong.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by QUOC CUONG on 02/11/2017.
 *
 * You can avoid the problem of lagging or not responding your activity by service.
 * And you no need to stop service. Whenever service is finished, the system will automatically
 * stop service.
 */

public class SecondService extends IntentService {

    public SecondService() {
        super("Second service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service is created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service is started!", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Getting intent from onStartCommand
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Cuong", "from the onHandleIntent method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service is stopped!", Toast.LENGTH_SHORT).show();
    }
}
