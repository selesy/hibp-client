package com.selesy.hibp;

import java.net.URL;
import java.util.stream.Stream;

/**
 * Provides an API for retrieving HIBP data from a server that both Java and
 * GWT (with its limited JRE compatibility) can support.  It is expected that
 * the individual clients will supply an HibpConnectionFactory for their
 * preferred connection.  Note that each connection is used for a single
 * request.
 */
public interface HibpConnection extends AutoCloseable {

    /**
     * Supplies a stream of String objects, each representing one line
     * returned from an HIBP server.  The contents of that line are expected
     * to contain a hashSuffix and a count separated by a colon which can
     * be parsed into an HibpResponse.  Note that the actual parsing of
     * String was moved into HibpClientBase to eliminate duplication.
     * 
     * @returns An HibpResponse in its marshalled form.
     */
    Stream<String> stream();
    
}