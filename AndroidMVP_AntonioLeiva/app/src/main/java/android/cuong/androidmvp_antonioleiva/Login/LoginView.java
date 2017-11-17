package android.cuong.androidmvp_antonioleiva.Login;

/**
 * Created by QUOC CUONG on 16/11/2017.
 */

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
    
}
