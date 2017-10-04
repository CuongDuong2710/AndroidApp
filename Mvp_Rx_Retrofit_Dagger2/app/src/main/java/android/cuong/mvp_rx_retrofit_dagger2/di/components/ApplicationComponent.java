package android.cuong.mvp_rx_retrofit_dagger2.di.components;

import android.cuong.mvp_rx_retrofit_dagger2.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * The component might be used throughout the entire app @Singleton.
 * And each component at least issued refer to two modules or dependencies
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
}
