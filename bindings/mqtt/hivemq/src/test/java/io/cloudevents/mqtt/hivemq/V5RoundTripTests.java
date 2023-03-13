/*
 * Copyright 2018-Present The CloudEvents Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.cloudevents.mqtt.hivemq;

import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishBuilder;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.format.EventFormat;
import io.cloudevents.core.message.MessageReader;
import io.cloudevents.core.message.MessageWriter;
import io.cloudevents.core.mock.CSVFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Round-Trip Tests
 * <p>
 * For both Binary and Structured modes:
 * - serialize a CloudEvent into an MQTT Message.
 * - de-serialize the message into a new CloudEvent
 * - verify that the new CE matches the original CE
 */
public class V5RoundTripTests {

    private static void readAndVerify(CloudEvent ce, Mqtt5Publish message) {

        Assertions.assertNotNull(message);

        // Read the message back into an event
        MessageReader reader = MqttMessageFactory.createReader(message);
        Assertions.assertNotNull(reader);

        CloudEvent newCE = reader.toEvent();
        Assertions.assertNotNull(newCE);

        // And now ensure we got back what we wrote
        Assertions.assertEquals(ce, newCE);
    }

    @ParameterizedTest
    @MethodSource("io.cloudevents.core.test.Data#allEventsWithoutExtensions")
    public void roundTripBinary(CloudEvent ce) {

        // Write the event out as a message.
        Mqtt5Publish message = null;
        Mqtt5PublishBuilder.Complete builder = (Mqtt5PublishBuilder.Complete) Mqtt5Publish.builder();
        builder.topic("test.test.test");


        MessageWriter writer = MqttMessageFactory.createWriter(builder);
        Assertions.assertNotNull(writer);

        writer.writeBinary(ce);

        message = builder.build();

        // Read it back and verify
        readAndVerify(ce, message);
    }

    @ParameterizedTest
    @MethodSource("io.cloudevents.core.test.Data#allEventsWithoutExtensions")
    public void roundTripStructured(CloudEvent ce) {

        EventFormat format = CSVFormat.INSTANCE;

        Mqtt5Publish message = null;
        Mqtt5PublishBuilder.Complete builder = (Mqtt5PublishBuilder.Complete) Mqtt5Publish.builder();
        builder.topic("test.test.test");

        // Write the event out as a message.
        MessageWriter writer = MqttMessageFactory.createWriter(builder);
        Assertions.assertNotNull(writer);

        writer.writeStructured(ce, format);

        message = builder.build();

        // Read it back and verify
        readAndVerify(ce, message);

    }


}
