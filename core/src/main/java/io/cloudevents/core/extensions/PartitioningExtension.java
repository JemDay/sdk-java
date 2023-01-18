package io.cloudevents.core.extensions;

import io.cloudevents.CloudEventExtension;
import io.cloudevents.CloudEventExtensions;
import io.cloudevents.core.extensions.impl.ExtensionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Supports the "Partitioning Extension"
 */
public class PartitioningExtension implements CloudEventExtension {

    private static final String ATTR_PARTITIONKEY = "partitionkey";

    private static final Set<String> KEYS;

    //-- Instance Variables

    private String partitionKey = null;

    // Initializer

    static {
        Set<String> keys = new HashSet<>(1);
        keys.add(ATTR_PARTITIONKEY);

        KEYS = Collections.unmodifiableSet(keys);
    }

    /**
     * @return the {@code partionkey} contained in this extension.
     */
    public String getPartitionKey() {
        return partitionKey;
    }

    /**
     * Set the {@code partiionkey} contained in this extension.
     * @param partitionKey
     */
    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    @Override
    public void readFrom(CloudEventExtensions extensions) {

        Object pk = extensions.getExtension(ATTR_PARTITIONKEY);

        if (pk != null) {
            partitionKey = pk.toString();
        }

    }

    @Override
    public Object getValue(String key) throws IllegalArgumentException {
        if (ATTR_PARTITIONKEY.equals(key)) {
            return this.partitionKey;
        }
        throw ExtensionUtils.generateInvalidKeyException(this.getClass(), key);
    }

    @Override
    public Set<String> getKeys() {
        return KEYS;
    }
}
