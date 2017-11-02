package android.cuong.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by QUOC CUONG on 02/11/2017.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Life cycle of service.
     * When call service first, it will call onCreate, onStartCommand.
     * If we call again (service still runs in background & do not stop),
     * it will call onStartCommand. It does not call onCreate.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service is created!", Toast.LENGTH_SHORT).show();
    }

    /**
     * onStartCommand will get data by intent
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service is started!", Toast.LENGTH_SHORT).show();

        // Get data from intent
        String message = intent.getStringExtra("message");
        Toast.makeText(this, "Message: " + message, Toast.LENGTH_SHORT).show();

        // It calls onDestroy
        //stopSelf();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service is stopped!", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
