package org.soundtouch4j.bosealarmclock;

import android.os.AsyncTask;
import android.util.Log;

import org.soundtouch4j.SoundTouchApiException;

import java.io.IOException;

public abstract class AbstractAsyncTask extends AsyncTask<Void, Void, Boolean> {

    // For Local Testing.
    // private static final String BASE_PATH = "http://geralds-mbp.fritz.box:1234/";
    // Static URL to my SoundTouch. Yes I'm owning a Fritzbox ;)
    protected static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090";

    @Override
    protected final Boolean doInBackground(final Void... ignored) {
        try {
            doInBackground();
            return true;
        } catch (final IOException | SoundTouchApiException e) {
            Log.e(MainActivity.TAG, "Error during doInBackground " + e.getMessage());
            return false;
        }
    }

    @Override
    protected final void onPostExecute(final Boolean success) {
        super.onPostExecute(success);
    }

    protected abstract void doInBackground() throws IOException, SoundTouchApiException;
}
