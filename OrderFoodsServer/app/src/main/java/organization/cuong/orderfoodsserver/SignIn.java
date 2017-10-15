package organization.cuong.orderfoodsserver;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hoang8f.widget.FButton;
import organization.cuong.orderfoodsserver.Model.User;

public class SignIn extends AppCompatActivity {
    @BindView(R.id.edtPhone) MaterialEditText edtPhone;
    @BindView(R.id.edtPassword) MaterialEditText edtPassword;
    @BindView(R.id.btnSignIn) FButton btnSignIn;

    FirebaseDatabase db = null;
    DatabaseReference users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        // Init Firebase
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        // sign in
        onClickSignIn();
    }

    /**
     * Clicking sign in button
     */
    private void onClickSignIn() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser(edtPhone.getText().toString(), edtPassword.getText().toString());
            }
        });
    }

    /**
     * Sign in to app
     * @param phone
     * @param password
     */
    private void signInUser(final String phone, final String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please waiting...");
        mDialog.show();

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Check if user is exits
                if(dataSnapshot.child(phone).exists()) {
                    mDialog.dismiss();
                    // get user and set phone
                    User user = dataSnapshot.child(phone).getValue(User.class);
                    user.setPhone(phone);
                    // check user is staff
                    if(Boolean.parseBoolean(user.getIsStaff())) {
                        // check password
                        if(user.getPassword().equals(password)) {
                            // Login ok
                        } else {
                            Toast.makeText(SignIn.this, "Wrong pass!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignIn.this, "Please login with Staff account!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User is not exits!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
