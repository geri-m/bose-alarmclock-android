package at.madlmayr.bosealarmclock.key;

import com.google.api.client.util.Key;

/**
 * Response from the '/key' POST Call
 *
 * So far, the response is always
 *
 * <?xml version="1.0" encoding="UTF-8" ?>
 * <status>/key</status>
 */


public class KeyResponse {


    @Key("text()")
    private String value;

    public KeyResponse() {

    }

    public String getValue() {
        return value;
    }
}
