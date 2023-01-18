package io.cloudevents.core.extensions;

import io.cloudevents.CloudEventExtension;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseExtensionTest {
    protected void assertKeys(CloudEventExtension e, String... keys) {

        // Basic Sanity Checks
        Assertions.assertNotNull(e.getKeys());
        Assertions.assertEquals(keys.length, e.getKeys().size());

        // All keys should be present
        Arrays.stream(keys).forEach(s -> {
            Assertions.assertTrue(e.getKeys().contains(s));
        });
    }

    protected void assertValue(CloudEventExtension e, String key, Object exp) {

        Object val = e.getValue(key);
        assertNotNull(val);
        assertEquals(exp.getClass(), val.getClass());
        assertEquals(exp, val);
    }
}
