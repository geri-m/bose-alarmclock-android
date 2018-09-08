package at.madlmayr.bosealarmclock.nowplaying;

import com.google.api.client.util.Key;

public class NowPlayingContentItem {

    @Key("@source")
    private SourceEnum source;

    public NowPlayingContentItem() {

    }

    public SourceEnum getSource() {
        return source;
    }
}
