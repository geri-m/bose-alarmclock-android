package org.soundtouch4j.bosealarmclock;


import com.google.api.client.http.javanet.NetHttpTransport;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

import java.io.IOException;
import java.net.URL;

/**
 * AsyncTask for triggering power on/off of SoundTouch
 */

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    @Override
    protected void doInBackground() throws IOException, SoundTouchApiException {
        new SoundTouchApi(new URL(BASE_PATH), new NetHttpTransport()).getKeyApi().power();
    }

}
