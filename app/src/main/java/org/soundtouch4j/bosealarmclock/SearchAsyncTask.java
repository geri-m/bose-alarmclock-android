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
                Log.i(MainActivity.TAG, "Found service at IP: " + service.getRemoteIp().toString());
            }

            @Override
            public void onServiceAnnouncement(final SsdpServiceAnnouncement announcement) {
                Log.i(MainActivity.TAG, "Service announced something: " + announcement);
            }

            @Override
            public void onFailed(final Exception ex) {
                Log.i(MainActivity.TAG, "Service onFailed: " + ex.getMessage());
            }
        });

        // We can do that for now, as we are on a separate thread anyway. Not nice, but does the job.
        try {
            Thread.sleep(10000);
        } catch (final InterruptedException e) {
            Log.e(MainActivity.TAG, "Error on Sleep");
        }

        Log.i(MainActivity.TAG, "Discovery Stopped");
        client.stopDiscovery();
    }
}
