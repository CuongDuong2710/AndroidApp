package android.cuong.mvp_rx_retrofit_dagger2.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * High scope is the singleton. That means like the application which it will be live throughout
 * the entire journey of the app.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
