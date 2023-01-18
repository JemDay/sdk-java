package io.cloudevents.core.extensions;

import org.junit.jupiter.api.Test;

class PartitioningExtensionTest extends BaseExtensionTest {

    private PartitioningExtension ext = new PartitioningExtension();

    @Test
    void getValue() {

        ext.setPartitionKey("ABCDEFG");

        assertValue(ext, "partitionkey", "ABCDEFG");
    }

    @Test
    void getKeys() {

        assertKeys(ext, "partitionkey");
    }
}
