package io.unlaunch.sdk;

import io.unlaunch.UnlaunchAttribute;
import io.unlaunch.UnlaunchClient;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * An example showing how to pass attributes. It uses a sample feature flag.
 * For more information on Attributes, see: https://docs.unlaunch.io/docs/features/attributes
 */
public class HelloAttributes {
    private static final String SDK_KEY = "prod-public-c1967ee6-f9f3-43c5-ae40-7f8ab6d725cd";
    private static final String FEATURE_FLAG_KEY = "mfa-feature";
    private static final Logger LOG = LogManager.getLogger(HelloAttributes.class);

    public static void main(String[] args) {
        // Create UnlaunchClient
        UnlaunchClient client = UnlaunchClient.create(SDK_KEY);

        // Wait for the client to be ready
        try {
            client.awaitUntilReady(2, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException e) {
            LOG.error("[DEMO] client wasn't ready " + e.getMessage());
        }
        

        Set<String> userSet = new HashSet<>();
        userSet.add("1");
        userSet.add("2");
        
        // Get variation for random identity and attributes
        String variation = client.getVariation(
                FEATURE_FLAG_KEY,
                UUID.randomUUID().toString(),
                // Pass the registered attribute as true which returns "true".
                // Set this to false to get the "off" variation
                UnlaunchAttribute.newBoolean("registered", true),
                UnlaunchAttribute.newString("device", "ABCS"),
                UnlaunchAttribute.newNumber("age", 30),
                UnlaunchAttribute.newDate("start_date", System.currentTimeMillis()),
                UnlaunchAttribute.newDateTime("expiry_date", System.currentTimeMillis()),
                UnlaunchAttribute.newSet("user_ids", userSet));

        // Print variation
        LOG.info("[DEMO] getVariation() returned {}", variation);

        if (variation.equals("control")) {
            LOG.info("'[DEMO] control' variation indicates that Unlaunch Client didn't connect with the server of the" +
                    " flag wasn't found.");
        }

        // shutdown the client to flush any events or metrics
        client.shutdown();
    }
}
