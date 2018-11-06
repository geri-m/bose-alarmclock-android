package org.soundtouch4j.bosealarmclock;


import com.google.api.client.extensions.android.http.AndroidHttp;

import org.soundtouch4j.SoundTouchApi;
import org.soundtouch4j.SoundTouchApiException;

import java.io.IOException;
import java.net.URL;

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    @Override
    protected void doInBackground() throws IOException, SoundTouchApiException {
        new SoundTouchApi(new URL(BASE_PATH), AndroidHttp.newCompatibleTransport()).getKeyApi().power();
    }

}
