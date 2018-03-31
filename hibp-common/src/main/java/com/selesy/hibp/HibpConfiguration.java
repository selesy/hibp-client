package com.selesy.hibp;

import lombok.Value;
import lombok.Builder;

/**
 * Provides an immutable configuration object as well as a builder that are
 * used to set-up the connections and algorithms the client uses to compare
 * a local password to the remote server.  A default configuration is built
 * from the values provided by HibpConstants.
 */
@Value
@Builder
public class HibpConfiguration {

    @Builder.Default String hibpUrl = HibpConstants.HIBP_URL;
    @Builder.Default String hibpMethod = HibpConstants.HIBP_METHOD;
    @Builder.Default String hibpAcceptHeader = HibpConstants.HIBP_ACCEPT_HEADER;
    @Builder.Default String hibpUserAgentHeader = HibpConstants.HIBP_USER_AGENT_HEADER;

    @Builder.Default String hashAlgorithm = HibpConstants.HASH_ALGORITHM;
    @Builder.Default String hashCharacterSet = HibpConstants.HASH_CHARACTER_SET;
    @Builder.Default int hashSplitPosition = HibpConstants.HASH_SPLIT_POSITION;
    @Builder.Default int hashLength = HibpConstants.HASH_LENGTH;

    public static final HibpConfiguration DEFAULT = HibpConfiguration.builder().build();

}
