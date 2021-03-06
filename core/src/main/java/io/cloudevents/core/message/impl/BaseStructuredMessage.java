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

package io.cloudevents.core.message.impl;

import io.cloudevents.core.message.Encoding;
import io.cloudevents.core.message.Message;
import io.cloudevents.visitor.CloudEventAttributesVisitor;
import io.cloudevents.visitor.CloudEventExtensionsVisitor;
import io.cloudevents.visitor.CloudEventVisitor;
import io.cloudevents.visitor.CloudEventVisitorFactory;

public abstract class BaseStructuredMessage implements Message {

    @Override
    public Encoding getEncoding() {
        return Encoding.STRUCTURED;
    }

    @Override
    public <V extends CloudEventVisitor<R>, R> R visit(CloudEventVisitorFactory<V, R> visitorFactory) {
        throw MessageUtils.generateWrongEncoding(Encoding.BINARY, Encoding.STRUCTURED);
    }

    @Override
    public void visitAttributes(CloudEventAttributesVisitor visitor) throws RuntimeException {
        throw MessageUtils.generateWrongEncoding(Encoding.BINARY, Encoding.STRUCTURED);
    }

    @Override
    public void visitExtensions(CloudEventExtensionsVisitor visitor) throws RuntimeException {
        throw MessageUtils.generateWrongEncoding(Encoding.BINARY, Encoding.STRUCTURED);
    }
}
