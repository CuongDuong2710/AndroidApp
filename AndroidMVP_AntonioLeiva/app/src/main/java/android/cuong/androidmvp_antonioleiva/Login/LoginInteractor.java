package android.cuong.androidmvp_antonioleiva.Login;

/**
 * Declaring login method and finished action listener interface
 * Created by QUOC CUONG on 16/11/2017.
 */

public interface LoginInteractor {

    void login(String username, String password, OnLoginFinishedListener listener);

    interface OnLoginFinishedListener {

        void onUsernameError();

        void onPasswordError();

        void onSuccess();

    }
}
