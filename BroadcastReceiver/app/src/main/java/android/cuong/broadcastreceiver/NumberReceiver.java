package android.cuong.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

/**
 * Created by QUOC CUONG on 07/11/2017.
 */

public class NumberReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // getting state of phone
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) { // if state is ringing (incoming call)

            // getting incoming number
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            // init DBHelper and saving incoming number to database
            DBHelper dbHelper = new DBHelper(context);

            // getting database to write data
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            // saving incoming number to database
            dbHelper.saveNumber(number, database);

            // close
            dbHelper.close();
        }

        // sending broadcast to update ui
        Intent intent1 = new Intent(DBContract.UPDATE_UI_FILTER);
        context.sendBroadcast(intent1);
    }
}
