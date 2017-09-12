package in.android.cuong.firebasetutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText mEditName;
    private Button mFireBasebtn;

    private String name = "";

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditName = (EditText) findViewById(R.id.edt_name);

        mFireBasebtn = (Button) findViewById(R.id.firebase_btn);

        mFireBasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getText from inside click listener, outside is ""
                name = mEditName.getText().toString().trim();

                // 1 - Create child in root object
                // 2 - Assign some value to the child object

                mDatabase.child("Name").setValue(name);

            }
        });
    }
}
