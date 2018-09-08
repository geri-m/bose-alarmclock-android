package at.madlmayr.bosealarmclock.nowplaying;

import com.google.api.client.util.Value;

/**
 * Enumeration fo the State of Playing of the SoundTouch
 */

public enum SourceEnum {

    // There are more Values to come. There are basically two states. Standby and "playing"

    @Value
    INTERNET_RADIO,

    @Value
    STANDBY
}
