package org.soundtouch4j.bosealarmclock;

import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.util.Key;
import com.google.api.client.xml.XmlNamespaceDictionary;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void test01_serializingXMLTest() throws XmlPullParserException, IOException {
        final XmlNamespaceDictionary dict = new XmlNamespaceDictionary().set("", "");
        final XmlHttpContent xmlContentForPostCall = new XmlHttpContent(dict, "AnyType", new AnyType());

        final OutputStream os = new ByteArrayOutputStream();
        xmlContentForPostCall.writeTo(os);
        System.out.println(os.toString());

    }

    public static class AnyType {
        @Key("@attr")
        public String attr;
        @Key
        public String elem;
        @Key
        public String rep;
        @Key
        public String value;
    }


}