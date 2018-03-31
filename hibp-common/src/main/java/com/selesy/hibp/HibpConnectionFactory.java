package com.selesy.hibp;

/**
 * Provides the API for a generic connection factory that specific clients
 * can implement.  This allows the HibpClientBase to be implementation
 * agnotistic while retaining the ability to create as many connections
 * as it needs during the course of its work.
 */
@FunctionalInterface
public interface HibpConnectionFactory {

    /**
     * Creates an HIBP compatible connection (in part defined by the values
     * in the configuration file), connects to the remote server and allows
     * the caller to access a Stream containing the lines of the text/plain
     * response entity body.
     * 
     * @returns A configured and connected HibpConnection.
     */
    HibpConnection connect(HibpConfiguration configuration, String hashPrefix);
    
}
