package com.selesy.hibp;

import java.util.stream.Stream;

import lombok.Value;

/**
 * Provides a DTO that encapsulates each line of the response from the HIBP
 * servers after it's been unmarshaled into the hash suffix and count
 * values.  A factory is also provided to unmarshal HibpResponse objects
 * from their String (colon separated) representation.
 */
@Value
public class HibpResponse {

    public class Factory {

        HibpConfiguration configuration;

        public Factory(HibpConfiguration configuration) {
            this.configuration = configuration;
        }

        public HibpResponse create(String responseLine) {
            String hashSuffix = responseLine.substring(0, configuration.getHashSplitPosition());
            long count = Long.parseLong(responseLine.substring(configuration.getHashSplitPosition()));
            return new HibpResponse(hashSuffix, count);
        }

        public Stream<HibpResponse> stream(Stream<String> responseLines) {
            return responseLines.map(this::create);
        }

    }

    String suffix;
    Long count;
    
    private HibpResponse(String suffix, Long count) {
        this.suffix = suffix;
        this.count = count;
    }

}