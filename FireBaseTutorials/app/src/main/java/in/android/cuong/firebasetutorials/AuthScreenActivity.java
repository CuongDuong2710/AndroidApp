package in.android.cuong.firebasetutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AuthScreenActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_screen);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnAuth = (Button) findViewById(R.id.btn_signup);
    }
}
