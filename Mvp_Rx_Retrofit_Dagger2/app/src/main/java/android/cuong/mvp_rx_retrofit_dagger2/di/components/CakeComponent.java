package android.cuong.mvp_rx_retrofit_dagger2.di.components;

import android.cuong.mvp_rx_retrofit_dagger2.di.module.CakeModule;
import android.cuong.mvp_rx_retrofit_dagger2.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by QUOC CUONG on 04/10/2017.
 */
@PerActivity
@Component(modules = CakeModule.class, dependencies = ApplicationComponent.class)
public interface CakeComponent {
}
