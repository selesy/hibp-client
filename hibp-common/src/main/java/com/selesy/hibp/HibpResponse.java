package com.selesy.hibp;

import lombok.Value;

/**
 * Provides a DTO that encapsulates each line of the response from the HIBP
 * servers after it's been unmarshaled into the hash suffix and count
 * values.
 */
@Value
public class HibpResponse {

    String suffix;
    Long count;
    
}