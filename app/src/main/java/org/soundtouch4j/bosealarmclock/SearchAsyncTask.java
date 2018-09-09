package org.soundtouch4j.bosealarmclock;

import android.util.Log;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

public class SearchAsyncTask extends AbstractAsyncTask {


    @Override
    protected void doInBackground() {

        final SsdpClient client = SsdpClient.create();
        final DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
                .serviceType("urn:schemas-upnp-org:device:MediaRenderer:1")
                .build();
        client.discoverServices(networkStorageDevice, new DiscoveryListener() {
            @Override
            public void onServiceDiscovered(final SsdpService service) {
                Log.i(MainActivity.TAG, "Found service: " + service);
            }

            @Override
            public void onServiceAnnouncement(final SsdpServiceAnnouncement announcement) {
                Log.i(MainActivity.TAG, "Service announced something: " + announcement);
            }

            @Override
            public void onFailed(Exception ex) {
                Log.i(MainActivity.TAG, "Service onFailed: " + ex.getMessage());
            }
        });

    }
}
