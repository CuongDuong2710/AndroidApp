package android.cuong.androidmvp_antonioleiva.Login;

/**
 * Created by QUOC CUONG on 16/11/2017.
 */

public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
