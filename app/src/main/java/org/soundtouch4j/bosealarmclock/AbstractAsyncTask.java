package org.soundtouch4j.bosealarmclock;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public abstract class AbstractAsyncTask extends AsyncTask<Void, Void, Boolean> {

    @Override
    protected final Boolean doInBackground(final Void... ignored) {
        try {
            doInBackground();
            return true;
        } catch (final IOException e) {
            Log.e(MainActivity.TAG, "Error during doInBackground " + e.getMessage());
            return false;
        }
    }

    @Override
    protected final void onPostExecute(final Boolean success) {
        super.onPostExecute(success);

    }

    protected abstract void doInBackground() throws IOException;
}
