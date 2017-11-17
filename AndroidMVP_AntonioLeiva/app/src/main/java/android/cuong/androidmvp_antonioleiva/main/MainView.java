package android.cuong.androidmvp_antonioleiva.main;

import java.util.List;

/**
 * Created by QUOC CUONG on 17/11/2017.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String message);
}
