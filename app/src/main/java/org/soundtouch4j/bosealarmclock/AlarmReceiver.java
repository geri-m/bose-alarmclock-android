package org.soundtouch4j.bosealarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Alarm Receive that gets triggered/receives the Intent on the Alaram we have.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i(MainActivity.TAG, "Alarm Triggered");
        new PowerButtonAsyncTask().execute();
    }
}
