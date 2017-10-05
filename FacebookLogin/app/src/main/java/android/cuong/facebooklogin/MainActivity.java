package android.cuong.facebooklogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private TextView textView;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call sdkInitialize before setContentView
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.fb_login_btn);
        textView = (TextView) findViewById(R.id.text_status);
        callbackManager = CallbackManager.Factory.create();

        // Register callback
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText("Login Success \n" +
                        loginResult.getAccessToken().getUserId() +
                        "\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                textView.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        /* Get keyhash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("android.cuong.facebooklogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
