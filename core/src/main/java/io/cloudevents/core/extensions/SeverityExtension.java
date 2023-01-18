package io.cloudevents.core.extensions;

import io.cloudevents.CloudEventExtension;
import io.cloudevents.CloudEventExtensions;
import io.cloudevents.core.extensions.impl.ExtensionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Supports the 'Severity Extension"
 */
public class SeverityExtension implements CloudEventExtension {

    private static final String ATTR_SEVERITY_TEXT = "severitytext";
    private static final String ATTR_SEVERITY_NUM = "severitynumber";
    private static final Set<String> KEYS;

    static {
        Set<String> keys = new HashSet<>(2);
        keys.add(ATTR_SEVERITY_NUM);
        keys.add(ATTR_SEVERITY_TEXT);
        KEYS = Collections.unmodifiableSet(keys);
    }

    private String severityText;
    private Integer severityNumber;

    public String getSeverityText() {
        return severityText;
    }

    public void setSeverityText(String severityText) {
        this.severityText = severityText;
    }

    public Integer getSeverityNumber() {
        return severityNumber;
    }

    public void setSeverityNumber(Integer severityNumber) {
        this.severityNumber = severityNumber;
    }

    @Override
    public void readFrom(CloudEventExtensions extensions) {

        severityText = readString(extensions, ATTR_SEVERITY_TEXT);
        severityNumber = readInteger(extensions, ATTR_SEVERITY_NUM);

    }

    @Override
    public Object getValue(String key) throws IllegalArgumentException {

        switch (key) {

            case ATTR_SEVERITY_NUM: {
                return severityNumber;
            }

            case ATTR_SEVERITY_TEXT: {
                return severityText;
            }

            default: {
                throw ExtensionUtils.generateInvalidKeyException(this.getClass(), key);
            }

        }
    }

    @Override
    public Set<String> getKeys() {
        return KEYS;
    }

    private String readString(CloudEventExtensions exts, String name) {
        final Object obj = exts.getExtension(name);

        return (obj != null) ? obj.toString() : null;
    }


    private Integer readInteger(CloudEventExtensions exts, String name) {
        final Object obj = exts.getExtension(name);

        if (obj != null) {

            if (obj instanceof Integer) {
                return (Integer) obj;
            } else if (obj instanceof String) {
                return Integer.valueOf((String) obj);
            }
        }

        return null;
    }
}
