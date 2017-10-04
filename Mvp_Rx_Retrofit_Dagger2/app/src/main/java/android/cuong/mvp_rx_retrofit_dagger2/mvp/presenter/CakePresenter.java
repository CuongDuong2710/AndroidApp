package android.cuong.mvp_rx_retrofit_dagger2.mvp.presenter;

import android.cuong.mvp_rx_retrofit_dagger2.base.BasePresenter;
import android.cuong.mvp_rx_retrofit_dagger2.mvp.view.MainView;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * We can communicate with a view so the view is already injected. It's main view is MainView and
 * be implemented by MainActivity
 */

public class CakePresenter extends BasePresenter<MainView> {

    public void getCakes() {
    }
}
