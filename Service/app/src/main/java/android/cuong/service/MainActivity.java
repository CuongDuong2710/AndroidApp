package android.cuong.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Service does not have GUI. It is always running in the background. Have two type services:
 * _ StartService: always running in the background. Means that if the activity start this service
 * is finished, the service still is running in the background.
 * _ Bounded Services: always bounded activity and will destroy automatically
 * when activity is finished.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Remember argument View
    public void startMethod(View v) {
        Intent intent = new Intent(this, SecondService.class);
        intent.putExtra("message", "This is a message");
        startService(intent);
    }

    public void stopMethod(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
