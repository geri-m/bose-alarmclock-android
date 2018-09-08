package at.madlmayr.bosealarmclock;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

import java.io.IOException;

import at.madlmayr.bosealarmclock.key.KeyPressAndReleaseEnum;
import at.madlmayr.bosealarmclock.key.KeyRequest;
import at.madlmayr.bosealarmclock.key.KeyResponse;
import at.madlmayr.bosealarmclock.key.KeyStateEnum;
import at.madlmayr.bosealarmclock.nowplaying.NowPlayingResponse;
import at.madlmayr.bosealarmclock.nowplaying.SourceEnum;

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    // We don't have a namespace for our Application and/or the objects. Soundtouch doesn't like it.
    private static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set("", "");

    // For Local Testing.
    // private static final String BASE_PATH = "http://geralds-mbp.fritz.box:1234/";
    // Static URL to my SoundTouch. Yes I'm owning a Fritzbox ;)
    private static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090/";
    private final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    private final GenericUrl postKey = new GenericUrl(BASE_PATH + "key");
    private final GenericUrl getNowPlaying = new GenericUrl(BASE_PATH + "now_playing");

    private final HttpRequestFactory factory = httpTransport.createRequestFactory();

    @Override
    protected void doInBackground() throws IOException {

        // https://github.com/google/google-http-java-client/blob/dev/google-http-client-xml/src/test/java/com/google/api/client/xml/XmlTest.java
        final NowPlayingResponse nowPlayingResponse = factory.buildGetRequest(getNowPlaying).setParser(new XmlObjectParser(DICTIONARY)).execute().parseAs(NowPlayingResponse.class);

        // If we are in Standby, start the SoundTouch
        if (nowPlayingResponse.getContentItem().getSource().equals(SourceEnum.STANDBY)) {
            Log.i(MainActivity.TAG, "SoundTouch in Standby, Power on");

            final XmlHttpContent contentPowerStep1 = new XmlHttpContent(DICTIONARY, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.PRESS.getValue()));
            final KeyResponse keyResponseStep1 = factory.buildPostRequest(postKey, contentPowerStep1).setParser(new XmlObjectParser(DICTIONARY)).execute().parseAs(KeyResponse.class);
            Log.i(MainActivity.TAG, keyResponseStep1.getValue());

            final XmlHttpContent contentPowerStep2 = new XmlHttpContent(DICTIONARY, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.RELEASE.getValue()));
            final KeyResponse keyResponseStep2 = factory.buildPostRequest(postKey, contentPowerStep2).setParser(new XmlObjectParser(DICTIONARY)).execute().parseAs(KeyResponse.class);
            Log.i(MainActivity.TAG, keyResponseStep2.getValue());
        } else {
            Log.i(MainActivity.TAG, "SoundTouch already started");
        }

    }
}
