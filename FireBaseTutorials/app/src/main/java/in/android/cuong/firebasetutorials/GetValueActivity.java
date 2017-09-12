package in.android.cuong.firebasetutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetValueActivity extends AppCompatActivity {

    private TextView mTextName;
    private TextView mTextAge;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_value);

        // create instance of FirebaseDatabase connect "https://fir-tutorials-1d984.firebaseio.com/Name"
        // remember change Rules of FirebaseDatabase-> ".read": "auth" == null
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Name");
        mTextName = (TextView) findViewById(R.id.txtName);
        mTextAge = (TextView) findViewById(R.id.txtAge);

        // Get value from firebase database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue().toString();

                mTextName.setText("Name: " + name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
