package com.selesy.hibp;

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
     * Provides the API for a generic connection factory that specific clients
     * can implement.  This allows the HibpClientBase to be implementation
     * agnotistic while retaining the ability to create as many connections
     * as it needs during the course of its work.
     */
    @FunctionalInterface
    public interface Factory {

        /**
         * Creates an HIBP compatible connection (in part defined by the values
         * in the configuration file), connects to the remote server and allows
         * the caller to access a Stream containing the lines of the text/plain
         * response entity body.
         * 
         * @returns A Possible object containing either aconfigured and connected
         *          HibpConnection or a Throwble describing the failure thrown
         *          while trying to create the connection.
         */
        Possible<HibpConnection> connect(HibpConfiguration configuration, String hashPrefix);

    }

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