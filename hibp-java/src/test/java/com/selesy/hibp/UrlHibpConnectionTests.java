package com.selesy.hibp;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;

@Log
public class UrlHibpConnectionTests {

    @Test
    void connectionsToBlackholeTimesOut() {
        HibpConfiguration configuration = HibpConfiguration.builder()
            .hibpUrl("https://blackhole.selesy.com/")
            .build();
        Possible<HibpConnection> connection = UrlHibpConnection.factory.connect(configuration, "12345");
        assertThat(connection.isPresent()).isFalse();
        assertThat(connection.error()).isExactlyInstanceOf(ConnectException.class);
    }

    @Test
    void connectionFailsToUnknownHost() {
        HibpConfiguration configuration = HibpConfiguration.builder()
            .hibpUrl("https://unknown.selesy.com/")
            .build();
        Possible<HibpConnection> connection = UrlHibpConnection.factory.connect(configuration, "12345");
        assertThat(connection.isPresent()).isFalse();
        assertThat(connection.error()).isExactlyInstanceOf(UnknownHostException.class);
    }

    @Test
    void connectionFailsWithMalformedUrl() {
        HibpConfiguration configuration = HibpConfiguration.builder()
            .hibpUrl("that's.not.a.url!")
            .build();
        Possible<HibpConnection> connection = UrlHibpConnection.factory.connect(configuration, "12345");
        assertThat(connection.isPresent()).isFalse();
        assertThat(connection.error()).isExactlyInstanceOf(MalformedURLException.class);
    }

    @Test
    void connectionToHibpSucceeds() {
        Possible<HibpConnection> connection = UrlHibpConnection.factory.connect(HibpConfiguration.DEFAULT, "12345");
        if(log.getLevel() == Level.FINE) {
            connection.ifPresent(c -> c.stream().forEach(s -> log.fine(() -> s)));
        }
        assertThat(connection.isPresent()).isTrue();
    }

}