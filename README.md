# bose-alarmclock-android

Create an Android Alarm Clock that triggers a Bose SoundTouch Product to Start playing.

## Research

I was unable to find a wrapper for Java for the API. Not to trick to implement. Elements are lower
case with is a bit strange. Values of Elements and Values of Attributes of the XML are case sensitive.

## HTTP Client Lib

For the REST Calls I'm trying the Google HTTP Client Library. I've not use this before, but I want 
to try something new anyway. I also supports XML as Body Format (but is beta), but hey: no risk no fun.

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



