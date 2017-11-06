package android.cuong.mvp_rx_retrofit_dagger2.di.components;

import android.content.Context;
import android.cuong.mvp_rx_retrofit_dagger2.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * The component might be used throughout the entire app @Singleton.
 * And each component at least issued refer to two modules or dependencies.
 * Component will expose services for client (entire app) using.
 * So scope of component will be Application and implement in Application class (CakeApplication)
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // Expose Retrofit and Context to CakeModule can able to access
    Retrofit exposeRetrofit();
    Context exposeContext();
}
