/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package cz.msebera.android.httpclient.impl.nio.codecs;

import java.io.IOException;

import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.message.LineFormatter;
import cz.msebera.android.httpclient.nio.reactor.SessionOutputBuffer;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.CharArrayBuffer;

/**
 * Default {@link cz.msebera.android.httpclient.nio.NHttpMessageWriter} implementation
 * for {@link HttpRequest}s.
 *
 * @since 4.1
 */
@SuppressWarnings("deprecation")
@NotThreadSafe
public class DefaultHttpRequestWriter extends AbstractMessageWriter<HttpRequest> {

    /**
     * @deprecated (4.3) use
     *   {@link DefaultHttpRequestWriter#DefaultHttpRequestWriter(SessionOutputBuffer, LineFormatter)}
     */
    @Deprecated
    public DefaultHttpRequestWriter(final SessionOutputBuffer buffer,
                             final LineFormatter formatter,
                             final HttpParams params) {
        super(buffer, formatter, params);
    }

    /**
     * Creates an instance of DefaultHttpRequestWriter.
     *
     * @param buffer the session output buffer.
     * @param formatter the line formatter If <code>null</code>
     *   {@link org.apache.http.message.BasicLineFormatter#INSTANCE} will be used.
     *
     * @since 4.3
     */
    public DefaultHttpRequestWriter(
            final SessionOutputBuffer buffer,
            final LineFormatter formatter) {
        super(buffer, formatter);
    }

    /**
     * @since 4.3
     */
    public DefaultHttpRequestWriter(final SessionOutputBuffer buffer) {
        super(buffer, null);
    }

    @Override
    protected void writeHeadLine(final HttpRequest message) throws IOException {
        final CharArrayBuffer buffer = lineFormatter.formatRequestLine(
                this.lineBuf, message.getRequestLine());
        this.sessionBuffer.writeLine(buffer);
    }

}
