package org.soundtouch4j.bosealarmclock;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;

import org.soundtouch4j.SoundTouch;
import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

import java.io.IOException;
import java.net.URL;

/**
 * Async Tasks for Powering on the SoundTouch if it is not running.
 */

public class PowerAlarmAsyncTask extends AbstractAsyncTask {


    @Override
    protected void doInBackground() throws IOException, SoundTouchApiException {
        final SoundTouch api = new SoundTouchApi(new URL(BASE_PATH), AndroidHttp.newCompatibleTransport());
        // If we are in Standby, start the SoundTouch
        if (api.getNowPlayingApi().nowPlaying().isInStandbyMode()) {
            Log.i(MainActivity.TAG, "SoundTouch in Standby, Power on");
            api.getKeyApi().power();
        } else {
            Log.i(MainActivity.TAG, "SoundTouch already started");
        }
    }
}
