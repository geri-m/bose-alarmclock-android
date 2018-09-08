package at.madlmayr.bosealarmclock;


// Not yet sure if this is the way to handle the XML Parsing on Android.
// https://stackoverflow.com/questions/5514752/xml-element-with-attribute-and-content-using-jaxb
// https://github.com/google/google-http-java-client/blob/dev/google-http-client-xml/src/test/java/com/google/api/client/xml/XmlTest.java

import com.google.api.client.util.Key;

public class KeyObject {


    @Key("@sender")
    private final String sender ="Gabbo";
    @Key("text()")
    private final String value;
    @Key("@state")
    private String state;

    public KeyObject(final String value, final String state){
        this.state = state;
        this.value = value;
    }

}
