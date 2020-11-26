/*
 * This is a demo HelloWorld app to describe how to use Unlaunch Java Sdk.
 * 
 */
package io.unlaunch.sdk;

import io.unlaunch.UnlaunchClient;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hello {

    private static final Logger LOG = LogManager.getLogger(Hello.class);

    // Set SDK_KEY to your Unlaunch SDK key 
    private static final String SDK_KEY = "";

    // Set FEATURE_FLAG_KEY to your feature flag key to evaluate
    private static final String FEATURE_FLAG_KEY = "";

    public static void main(String[] args) {
        if (SDK_KEY.equals("")) {
            LOG.error("Please edit Hello.java to set SDK_KEY to Unlaunch SDK key.");
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
        
        // shutdown the client to flush any events or metrics 
        client.shutdown();
    }
}
