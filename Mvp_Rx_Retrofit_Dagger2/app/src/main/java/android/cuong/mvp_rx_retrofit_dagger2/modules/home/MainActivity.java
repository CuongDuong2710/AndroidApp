package android.cuong.mvp_rx_retrofit_dagger2.modules.home;

import android.content.Intent;
import android.cuong.mvp_rx_retrofit_dagger2.R;
import android.cuong.mvp_rx_retrofit_dagger2.base.BaseActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }
}
