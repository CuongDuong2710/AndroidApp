package android.cuong.androidmvp_antonioleiva.main;

import java.util.List;

/**
 * Created by QUOC CUONG on 17/11/2017.
 */

interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
