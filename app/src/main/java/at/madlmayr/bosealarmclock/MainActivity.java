package at.madlmayr.bosealarmclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "BoseAlarmClock";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
    }

    public void pressPower(final View view) {
        Log.i(TAG, "pressPower");
        new PowerButtonAsyncTask().execute();


    }
}
