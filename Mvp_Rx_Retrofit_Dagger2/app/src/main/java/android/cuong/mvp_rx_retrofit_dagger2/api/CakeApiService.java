package android.cuong.mvp_rx_retrofit_dagger2.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * Connect to API
 * https://gist.githubusercontent.com/filippella/a728a34822a3bc7add98e477a4057b69/raw/310d712e87941f569074a63fedb675d2b611342a/cakes
 */

public interface CakeApiService {

    @GET("/filippella/a728a34822a3bc7add98e477a4057b69/raw/310d712e87941f569074a63fedb675d2b611342a/cakes")
    Observable<>
}
