package io.cloudevents.core.extensions;

import io.cloudevents.CloudEventExtension;
import io.cloudevents.CloudEventExtensions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SeverityExtensionTest extends BaseExtensionTest {


    private static final String ATTR_SEV_TEXT = "severitytext";
    private static final String ATTR_SEV_NUM = "severitynumber";

    @Test
    void getValue() {
        SeverityExtension se = new SeverityExtension();

        final String TXT = "Hello World";
        final Integer INT = 42;

        se.setSeverityNumber(INT);
        se.setSeverityText(TXT);


        assertValue(se, ATTR_SEV_TEXT, TXT);
        assertValue(se, ATTR_SEV_NUM, INT);
    }

    @Test
    void getKeys() {

        SeverityExtension se = new SeverityExtension();

        // Expected keys..
        assertKeys(se, "severitytext", "severitynumber");

    }
}
