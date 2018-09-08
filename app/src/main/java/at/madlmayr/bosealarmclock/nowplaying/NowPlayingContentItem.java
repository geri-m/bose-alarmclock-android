package at.madlmayr.bosealarmclock.nowplaying;

import com.google.api.client.util.Key;

public class NowPlayingContentItem {

    @Key("@source")
    private String source;

    public NowPlayingContentItem() {

    }


    public String getSource() {
        return source;
    }
}
