package at.madlmayr.bosealarmclock;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;

import java.io.IOException;

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

            // final HttpResponse infoResponse = factory.buildGetRequest(getNowPlaying).execute();
            final XmlHttpContent contentPowerStep1 = new XmlHttpContent(DICTIONARY, "key", new KeyObject("POWER", "press"));
            final HttpRequest request = factory.buildPostRequest(postKey, contentPowerStep1);
            request.getHeaders().setContentType("text/xml").setAcceptEncoding("gzip, deflate").setAccept("*//*");
            final HttpResponse response = request.execute();


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
