package android.cuong.mvp_rx_retrofit_dagger2.modules.home;

import android.content.Intent;
import android.cuong.mvp_rx_retrofit_dagger2.R;
import android.cuong.mvp_rx_retrofit_dagger2.base.BaseActivity;
import android.cuong.mvp_rx_retrofit_dagger2.di.components.DaggerApplicationComponent;
import android.cuong.mvp_rx_retrofit_dagger2.mvp.presenter.CakePresenter;
import android.cuong.mvp_rx_retrofit_dagger2.mvp.view.MainView;
import android.os.Bundle;

import javax.inject.Inject;

/**
 * MainActivity implement MainView which is injected in BasePresenter
 */
public class MainActivity extends BaseActivity implements MainView{

    @Inject protected CakePresenter mPresenter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        mPresenter.getCakes();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDaggerDependency() {
        DaggerApplicationComponent.create().exposeContext();
    }
}
