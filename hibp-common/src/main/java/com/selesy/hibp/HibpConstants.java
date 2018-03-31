package com.selesy.hibp;

import lombok.experimental.UtilityClass;

/**
 * Defines the constants that define the HIBP K-Anonymity algorithm as well
 * as the connection information needed to query the HIBP server.  These
 * constants are later turned int a HibpConfiguration.DEFAULT.
 */
@UtilityClass
public class HibpConstants {

    // Constants needed to create the connection to the HIBP server
    public static final String HIBP_URL = "https://api.pwnedpasswords.com/range/";
    public static final String HIBP_METHOD = "GET";
    public static final String HIBP_ACCEPT_HEADER = "text/plain";
    public static final String HIBP_USER_AGENT_HEADER = "HIBP Java Client";

    // Constants that determine how the K-Anonymity algorithm creates and
    // splits the hash it's comparing.
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final String HASH_CHARACTER_SET = "ISO-8859-1";
    public static final int HASH_SPLIT_POSITION = 5;
    public static final int HASH_LENGTH = 40;

}