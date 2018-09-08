package at.madlmayr.bosealarmclock;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
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

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    // We don't have a namespace for our Application.
    // Currently not in use.
    static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set("", "");


    // For Local Debugging of the XML Request I use a netcat, as there is very little docu on
    // the new Java HTTP Client from GOOGLE.
    // private static final String BASE_PATH = "http://geralds-mbp.fritz.box:1234/";
    // Static URL to my soundbar. Yes I'm owning a Fritzbox ;)

    private static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090/";
    private final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    private final GenericUrl postKey = new GenericUrl(BASE_PATH + "key");
    private final GenericUrl getNowPlaying = new GenericUrl(BASE_PATH + "now_playing");

    private final HttpRequestFactory factory = httpTransport.createRequestFactory();

    @Override
    protected void doInBackground() throws IOException {

        try {

            // https://github.com/google/google-http-java-client/blob/dev/google-http-client-xml/src/test/java/com/google/api/client/xml/XmlTest.java
            final HttpRequest nowPlayingRequest = factory.buildGetRequest(getNowPlaying);
            nowPlayingRequest.getHeaders().setAcceptEncoding("gzip, deflate").setAccept("*/*");
            nowPlayingRequest.setParser(new XmlObjectParser(DICTIONARY));
            final NowPlayingResponse nowPlayingResponse = nowPlayingRequest.execute().parseAs(NowPlayingResponse.class);
            Log.i(MainActivity.TAG, nowPlayingResponse.getContentItem().getSource());

            final XmlHttpContent contentPowerStep1 = new XmlHttpContent(DICTIONARY, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.PRESS.getValue()));
            final HttpRequest requestStep1 = factory.buildPostRequest(postKey, contentPowerStep1);
            requestStep1.getHeaders().setContentType("text/xml").setAcceptEncoding("gzip, deflate").setAccept("*/*");
            requestStep1.setParser(new XmlObjectParser(DICTIONARY));
            final KeyResponse keyResponseStep1 = requestStep1.execute().parseAs(KeyResponse.class);
            Log.i(MainActivity.TAG, keyResponseStep1.getValue());

            final XmlHttpContent contentPowerStep2 = new XmlHttpContent(DICTIONARY, KeyRequest.ELEMENT_NAME, new KeyRequest(KeyPressAndReleaseEnum.POWER.name(), KeyStateEnum.RELEASE.getValue()));
            final HttpRequest requestStep2 = factory.buildPostRequest(postKey, contentPowerStep2);
            requestStep2.getHeaders().setContentType("text/xml").setAcceptEncoding("gzip, deflate").setAccept("*/*");
            requestStep2.setParser(new XmlObjectParser(DICTIONARY));
            final KeyResponse keyResponseStep2 = requestStep2.execute().parseAs(KeyResponse.class);
            Log.i(MainActivity.TAG, keyResponseStep2.getValue());


            // Bose's API Document is not correct. It is sufficient to send a single "press" for the Power Button.
            // We are sending Text that is handcrafted for the beginning, which is sufficient.
/*
            if (infoResponse.parseAsString().contains("STANDBY")) {

                Log.i(TAG, "Box is in standby, Power on");

                final HttpRequest request = factory.buildPostRequest(postKey, ByteArrayContent.fromString(null, "<key state=\"press\" sender=\"Gabbo\">POWER</key>"));
                request.getHeaders().setContentType("text/xml").setAcceptEncoding("gzip, deflate").setAccept("*//*");
                final HttpResponse response = request.execute();
                Log.i(TAG, response.getStatusMessage());
            } else {
                Log.i(TAG, "Box is already running");
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
