package android.cuong.mvp_rx_retrofit_dagger2.application;

import android.app.Application;
import android.cuong.mvp_rx_retrofit_dagger2.di.components.DaggerApplicationComponent;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * Application is a single object which will be available throughout the entire lifecycle of app
 */

public class CakeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        DaggerApplicationComponent.builder.create();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
