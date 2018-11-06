package org.soundtouch4j.bosealarmclock;

import android.util.Log;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.DiscoveryRequest;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;

/**
 * {@link android.os.AsyncTask} to do a search for Bose SoundTouch Speakers via SSDP.
 */

public class SearchAsyncTask extends AbstractAsyncTask {

    private static final String BOSE_URN = "urn:schemas-upnp-org:device:MediaRenderer:1";
    private static final int SCAN_DURATION_IN_MS = 5000;

    @Override
    protected void doInBackground() {
        final SsdpClient client = SsdpClient.create();
        final DiscoveryRequest networkStorageDevice = DiscoveryRequest.builder()
                .serviceType(BOSE_URN)
                .build();
        client.discoverServices(networkStorageDevice, new DiscoveryListener() {
            @Override
            public void onServiceDiscovered(final SsdpService service) {
                Log.i(MainActivity.TAG, "Found service at IP: " + service.getRemoteIp().toString());
            }

            @Override
            public void onServiceAnnouncement(final SsdpServiceAnnouncement announcement) {

            }

            @Override
            public void onFailed(final Exception ex) {

            }
        });

        // Thread-Sleep is not nice, although AsyncTask
        try {
            Thread.sleep(SCAN_DURATION_IN_MS);
        } catch (final InterruptedException e) {
            // ignore
        }
    }
}
