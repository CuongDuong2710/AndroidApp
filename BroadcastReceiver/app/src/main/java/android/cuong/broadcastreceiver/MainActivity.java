package android.cuong.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Showing incoming number is getting from database
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<IncomingNumber> arrayList = new ArrayList<>();
    private RecyclerAdapter adapter = null;

    private BroadcastReceiver mBroadcastReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTextView = (TextView) findViewById(R.id.emptyText);

        // set layout for recycle view
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        // init adapter
        adapter = new RecyclerAdapter(arrayList);
        mRecyclerView.setAdapter(adapter);

        // reading from database
        readFromDb();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                readFromDb();
            }
        };
    }

    /**
     * Reading data from database and adding to array list
     */
    private void readFromDb() {
        // clearing data of array list
        arrayList.clear();

        // int DBHelper
        DBHelper dbHelper = new DBHelper(this);

        // Get database to write data
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // init Cursor to query database
        Cursor cursor = dbHelper.readNumber(database);

        // if table has data
        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) { //looping to get data
                String number;
                int id;
                number = cursor.getString(cursor.getColumnIndex(DBContract.INCOMING_NUMBER));
                id = cursor.getInt(cursor.getColumnIndex("id"));
                arrayList.add(new IncomingNumber(id, number));
            }
            // close cursor and database helper
            cursor.close();
            dbHelper.close();

            adapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, new IntentFilter(DBContract.UPDATE_UI_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }
}
