package at.madlmayr.bosealarmclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "BoseAlarmClock";
    // Static URL to my soundbar. Yes I'm owning a Fritzbox ;)
    private static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090/";
    private final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    private final GenericUrl baseUrl = new GenericUrl(BASE_PATH);
    private final HttpRequestFactory factory = httpTransport.createRequestFactory();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");
    }

    public void turnOn(final View view) {
        Log.i(TAG, "turnOn");
    }

    public void turnOff(final View view) {
        Log.i(TAG, "turnOff");
    }
}
