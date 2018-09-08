package at.madlmayr.bosealarmclock.nowplaying;


/*

<?xml version="1.0" encoding="UTF-8" ?>
<nowPlaying deviceID="C8DF84AE0B6E" source="INTERNET_RADIO">
    <ContentItem source="INTERNET_RADIO" location="45289" sourceAccount="" isPresetable="true">
        <itemName>Deep House Lounge</itemName>
        <containerArt>http://item.radio456.com/007452/logo/logo-45289.jpg</containerArt>
    </ContentItem>
    <track></track>
    <artist></artist>
    <album></album>
    <stationName>Deep House Lounge</stationName>
    <art artImageStatus="IMAGE_PRESENT">http://item.radio456.com/007452/logo/logo-45289.jpg</art>
    <playStatus>PLAY_STATE</playStatus>
    <description>MP3  128 kbps  Internet Only,  www.deephouselounge.com is an underground house and electronic dance music radio station / community.</description>
    <stationLocation>Internet Only</stationLocation>
</nowPlaying>
 */



/*
<?xml version="1.0" encoding="UTF-8" ?>
<nowPlaying deviceID="C8DF84AE0B6E" source="STANDBY">
    <ContentItem source="STANDBY" isPresetable="true" />
</nowPlaying>
 */

import com.google.api.client.util.Key;

public class NowPlayingResponse {

    @Key("ContentItem")
    private NowPlayingContentItem contentItem;

    NowPlayingResponse() {

    }

    public NowPlayingContentItem getContentItem() {
        return contentItem;
    }
}