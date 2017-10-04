package android.cuong.mvp_rx_retrofit_dagger2.di.module;

import android.cuong.mvp_rx_retrofit_dagger2.api.CakeApiService;
import android.cuong.mvp_rx_retrofit_dagger2.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by QUOC CUONG on 04/10/2017.
 */
@Module
public class CakeModule {

    @PerActivity
    @Provides
    CakeApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(CakeApiService.class);
    }
}
