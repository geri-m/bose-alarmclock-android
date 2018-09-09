# bose-alarmclock-android

Create an Android Alarm Clock that triggers a Bose SoundTouch Product to Start playing.

## Research

I was unable to find a wrapper for Java for the API. Not too tricky to implement. Elements in the XML 
are lower case with is a bit strange. Values of Elements and Values of Attributes of the XML are case 
sensitive.

## HTTP Client Lib

For the REST Calls I'm trying the Google HTTP Client Library. I've not used this before, but I want 
to try something new anyway. It also supports XML as Body Format (but is beta), but hey: no risk no fun.

This is a helpful example for the XML Usage of the Lib.
```
https://github.com/google/google-http-java-client/blob/dev/google-http-client-xml/src/test/java/com/google/api/client/xml/XmlTest.java
```

Therefore I'm adding 

```
    implementation ('com.google.http-client:google-http-client-android:1.25.0')  {
        exclude  module: 'httpclient'
        exclude   module: 'xpp3'
        exclude   module: 'commons-logging'
    }
    implementation ('com.google.http-client:google-http-client-xml:1.25.0' ) {
        exclude  module: 'httpclient'
        exclude  module: 'xpp3'
        exclude   module: 'commons-logging'
    }
```

to the ```build.gradle```. The 'exclude'  I have found in the example: https://github.com/google/google-api-java-client-samples/blob/master/tasks-android-sample/build.gradle

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