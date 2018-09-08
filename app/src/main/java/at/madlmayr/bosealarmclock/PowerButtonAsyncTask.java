package at.madlmayr.bosealarmclock;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;

import java.io.IOException;

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    // Static URL to my soundbar. Yes I'm owning a Fritzbox ;)
    private static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090/";
    private final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    private final GenericUrl baseUrl = new GenericUrl(BASE_PATH);
    private final HttpRequestFactory factory = httpTransport.createRequestFactory();

    @Override
    protected void doInBackground() throws IOException {
        final GenericUrl getInfo = baseUrl.clone();
        getInfo.appendRawPath("info");
        try {
            final HttpResponse infoResponse = factory.buildGetRequest(getInfo).execute();
            Log.i(MainActivity.TAG, infoResponse.getStatusMessage());
        } catch (final IOException e) {
            Log.e(MainActivity.TAG, "Error Getting Info");
        }
    }
}
