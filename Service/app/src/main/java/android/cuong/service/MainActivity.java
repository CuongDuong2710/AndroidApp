package android.cuong.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Remember agrument View
    public void startMethod(View v) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("message", "This is a message");
        startService(intent);
    }

    public void stopMethod(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
