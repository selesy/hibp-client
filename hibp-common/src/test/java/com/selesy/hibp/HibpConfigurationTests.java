package com.selesy.hibp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for HibpConfiguration.java
 */
public class HibpConfigurationTests {

    HibpConfiguration configuration = HibpConfiguration.DEFAULT;

    /**
     * This test simply verifies that the constants defined in HibpConstants
     * are properly loaded into the default HibpConfiguration.  Note that this
     * test is really more of a hedge against the Lombok API for @Builder,
     * @Builder.Default and @Value don't change in a way that breaks the HIBP
     * client's ability to connect to the server.
     */
    @Test
    @DisplayName("Default configuration is loaded from constants")
    void testDefaultConfiguration() {
        assertThat(configuration.getHibpUrl()).isEqualTo(HibpConstants.HIBP_URL);
        assertThat(configuration.getHibpMethod()).isEqualTo(HibpConstants.HIBP_METHOD);
        assertThat(configuration.getHibpAcceptHeader()).isEqualTo(HibpConstants.HIBP_ACCEPT_HEADER);
        assertThat(configuration.getHibpUserAgentHeader()).isEqualTo(HibpConstants.HIBP_USER_AGENT_HEADER);

        assertThat(configuration.getHashAlgorithm()).isEqualTo(HibpConstants.HASH_ALGORITHM);
        assertThat(configuration.getHashCharacterSet()).isEqualTo(HibpConstants.HASH_CHARACTER_SET);
        assertThat(configuration.getHashSplitPosition()).isEqualTo(HibpConstants.HASH_SPLIT_POSITION);
        assertThat(configuration.getHashLength()).isEqualTo(HibpConstants.HASH_LENGTH);
    }

}