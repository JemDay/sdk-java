package io.cloudevents.core.extensions;

import io.cloudevents.CloudEventExtension;
import io.cloudevents.CloudEventExtensions;
import io.cloudevents.core.extensions.impl.ExtensionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Supports the "Sampled Rate" Extension
 */
public class SampledRateExtension implements CloudEventExtension {

    private static final String ATTR_SAMPLED_RATE = "sampledrate";
    private static final Set<String> KEYS;

    // Instance Variables
    private Integer sampledRate;

    static {
        Set<String> keys = new HashSet<>(1);
        keys.add(ATTR_SAMPLED_RATE);
        KEYS = Collections.unmodifiableSet(keys);
    }

    public int getSampledRate() {
        return sampledRate;
    }

    public void setSampledRate(int sampledRate) {
        this.sampledRate = sampledRate;
    }

    @Override
    public void readFrom(CloudEventExtensions extensions) {

        Object obj = extensions.getExtension(ATTR_SAMPLED_RATE);

        if (obj != null) {

            if (obj instanceof Integer) {
                sampledRate = (Integer) obj;
            }
            else if (obj instanceof java.lang.String ) {
                sampledRate = Integer.valueOf((String) obj);
            }
        }
    }

    @Override
    public Object getValue(String key) throws IllegalArgumentException {

        if (key.equals(ATTR_SAMPLED_RATE)) {
            return sampledRate;
        }

        throw ExtensionUtils.generateInvalidKeyException(this.getClass(), key);
    }

    @Override
    public Set<String> getKeys() {
        return KEYS;
    }
}
