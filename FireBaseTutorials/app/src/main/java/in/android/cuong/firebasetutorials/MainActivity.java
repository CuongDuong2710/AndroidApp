package in.android.cuong.firebasetutorials;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText mEditName;
    private EditText mEditEmail;
    private Button mFireBasebtn;

    private String name = "";
    private String email = "";

    // declare DatabaseReference
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = (EditText) findViewById(R.id.edt_name);
        mEditEmail = (EditText) findViewById(R.id.edt_email);
        mFireBasebtn = (Button) findViewById(R.id.firebase_btn);

        // create instance of FirebaseDatabase connect "https://fir-tutorials-1d984.firebaseio.com/"
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFireBasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getText from inside click listener, outside is ""
                name = mEditName.getText().toString().trim();
                email = mEditEmail.getText().toString().trim();

                // create HashMap to store data
                HashMap<String, String> dataMap = new HashMap<>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);

                // 1 - Create child in root object
                // 2 - Assign some value to the child object

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isComplete()) {
                            Toast.makeText(MainActivity.this, "Stored...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
