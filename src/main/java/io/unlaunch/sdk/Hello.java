package io.unlaunch.sdk;

import io.unlaunch.UnlaunchClient;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is a demo app to describe how to use Unlaunch Java SDK.
 * @author fahmina
 */
public class Hello {

    private static final Logger LOG = LogManager.getLogger(Hello.class);

    // EDIT ME! Set SDK_KEY to your Unlaunch SDK key.
    private static final String SDK_KEY = "";

    // EDIT ME!  Set FEATURE_FLAG_KEY to your feature flag key to evaluate
    private static final String FEATURE_FLAG_KEY = "";

    public static void main(String[] args) {
        if (SDK_KEY == null || SDK_KEY.isEmpty()) {
            LOG.error("You must edit Hello.java to set SDK_KEY to Unlaunch SDK key. \n" +
                    "Please visit https://docs.unlaunch.io/docs/sdks/sdk-keys for more information.");
            System.exit(1);
        }

        if (FEATURE_FLAG_KEY == null || FEATURE_FLAG_KEY.isEmpty()) {
            LOG.error("You must edit Hello.java to set FEATURE_FLAG_KEY to key of the flag you wish to fetch. \n" +
                    "Please visit https://docs.unlaunch.io/docs/getting-started for more information.");
            System.exit(1);
        }

        // Create UnlaunchClient object simply by using SDK_KEY.          
        UnlaunchClient client = UnlaunchClient.create(SDK_KEY);

        // Wait for the client to be ready
        try {
            client.awaitUntilReady(2, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException e) {
            LOG.error("client wasn't ready " + e.getMessage());
        }

        // Get variation
        String variation = client.getVariation(FEATURE_FLAG_KEY, "user-id-123");
        
        LOG.info("Variation served is {}", variation);

        if (variation.equals("control")) {
            LOG.info("'control' variation indicates that Unlaunch Client didn't connect with the server.");
        }
        
        // shutdown the client to flush any events or metrics 
        client.shutdown();
    }
}
