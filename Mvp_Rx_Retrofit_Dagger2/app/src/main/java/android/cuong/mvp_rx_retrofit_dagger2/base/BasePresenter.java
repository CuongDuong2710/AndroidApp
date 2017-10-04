package android.cuong.mvp_rx_retrofit_dagger2.base;

import android.cuong.mvp_rx_retrofit_dagger2.mvp.view.BaseView;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by QUOC CUONG on 04/10/2017.
 * Subscribing everything should be protected
 */

public class BasePresenter<V extends BaseView> {

    @Inject protected V mView;

    protected V getView() {
        return mView;
    }

    // Subscribing can have a generic type
    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
