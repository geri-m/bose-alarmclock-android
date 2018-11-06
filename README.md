# bose-alarmclock-android

Create an Android Alarm Clock that triggers a Bose SoundTouch Product to Start playing.

## SoundTouch4J

In order to interact with the Bose Speaker, you can use the SoundTouch4J library (http://soundtouch4j.org).

```
implementation ('org.soundtouch4j:soundtouch4j-api:1.0.6') {
    exclude  module: 'httpclient'
    exclude  module: 'xpp3'
    exclude  module: 'commons-logging'
}
```
    
## Android Alarm Clock

There is a nice example on how to use the Android Alarm Clock and the Android References
- https://www.codingconnect.net/android-application-creates-alarm-clock/
- https://developer.android.com/reference/android/provider/AlarmClock

## Discovery

Initially I as looking int mDNS (Jmdns) as there is an example on github that relates to Bose 
(https://github.com/janschraepen/soundtouch/blob/master/src/main/java/eu/bose/soundtouch/mdns/Discoverer3.java).

I was not successful with this approach. After some research I used SSDP as there as the resourcepool 
SSDP Library works out of the box on Android (https://github.com/resourcepool/ssdp-client). The library is open
source as well and maintained activity

Dependency for SSDP Library

```
implementation 'io.resourcepool:ssdp-client:2.2.0'
```