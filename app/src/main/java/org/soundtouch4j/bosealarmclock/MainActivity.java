package org.soundtouch4j.bosealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "BoseAlarmClock";

    private TimePicker alarmTimePicker;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Log.i(TAG, "onCreate successfully completed");
    }

    /**
     * Callback from the GUI when pressing the "Toggle" button
     *
     * We suppress the Warning for Deprecation for APIs in that are deprecated Since API Level 23.
     *
     * @param view View passed from the GUI
     */
    @SuppressWarnings("deprecation")
    public void pressToggle(final View view) {
        long time;
        if (((Checkable) view).isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM is set to ON", Toast.LENGTH_SHORT).show();
            final Calendar calendar = Calendar.getInstance();

            // These Methods are deprecated since API Level 23. We are target 21, so we are good.
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            final Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM == 0) {
                    time = time + (1000 * 60 * 60 * 12);
                } else {
                    time = time + (1000 * 60 * 60 * 24);
                }
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(MainActivity.this, "ALARM is set to OFF", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method that is triggered from the UI to start searching for the Speakers
     *
     * @param view View passed from the GUI
     */
    public void searchForSpeaker(final View view) {
        Log.i(TAG, "searchForSpeaker");
    }
}
