package io.cloudevents.core.extensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampledRateExtensionTest extends BaseExtensionTest {

    @Test
    void getValue() {

        SampledRateExtension sre = new SampledRateExtension();

        sre.setSampledRate(42);
        assertValue(sre, "sampledrate", (Integer) 42);
    }

    @Test
    void getKeys() {

        SampledRateExtension sre = new SampledRateExtension();

        assertKeys(sre,"sampledrate");
    }
}
