package at.madlmayr.bosealarmclock;

import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.xml.XmlNamespaceDictionary;

import java.io.IOException;

public class PowerButtonAsyncTask extends AbstractAsyncTask {

    // Static URL to my soundbar. Yes I'm owning a Fritzbox ;)
    private static final String BASE_PATH = "http://soundtouch-20.fritz.box:8090/";
    static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary()
            //.set("", "http://www.w3.org/2005/Atom")
            .set("", "")
            .set("activity", "http://activitystrea.ms/spec/1.0/")
            .set("georss", "http://www.georss.org/georss")
            .set("media", "http://search.yahoo.com/mrss/")
            .set("thr", "http://purl.org/syndication/thread/1.0");
    //private static final String BASE_PATH = "http://geralds-mbp.fritz.box:1234/";
    private final HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
    private final GenericUrl getInfo = new GenericUrl(BASE_PATH + "info");
    private final GenericUrl postKey = new GenericUrl(BASE_PATH + "key");
    private final GenericUrl getNowPlaying = new GenericUrl(BASE_PATH + "new_playing");

    private final HttpRequestFactory factory = httpTransport.createRequestFactory();

    @Override
    protected void doInBackground() throws IOException {
        // final HttpResponse infoResponse = factory.buildGetRequest(getInfo).execute();
        // Log.i(MainActivity.TAG, infoResponse.getStatusMessage());

        // final XmlHttpContent contentPowerStep1 = new XmlHttpContent(DICTIONARY, null, "<key state=\"press\" sender\"Gabbo\">POWER</key>");
        // final HttpRequest request = factory.buildPostRequest(postKey, contentPowerStep1);

        final HttpRequest request = factory.buildPostRequest(postKey, ByteArrayContent.fromString(null, "<key state=\"press\" sender=\"Gabbo\">POWER</key>"));
        request.getHeaders().setContentType("text/xml").setAcceptEncoding("gzip, deflate").setAccept("*/*");
        final HttpResponse response = request.execute();
        Log.i(MainActivity.TAG, response.getStatusMessage());
    }
}
