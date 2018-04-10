package com.selesy.hibp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import lombok.extern.java.Log;

@Log
public class UrlHibpConnection implements HibpConnection {

	public static Factory factory = (configuration, hashPrefix) -> {
		try {
			URL url = new URL(configuration.getHibpUrl() + hashPrefix);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(configuration.getHibpMethod());
			connection.setRequestProperty("Accept", "text/plain");
			connection.setRequestProperty("User-Agent", "HIBP Java Client");
			connection.connect();
			return Possible.of(new UrlHibpConnection(connection));
		} catch (IOException e) {
			log.severe(() -> e.getClass().getSimpleName() + ": " + e.getMessage());
			return Possible.of(e);
		}
	};

	HttpURLConnection connection;

	private UrlHibpConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	@Override
	public Stream<String> stream() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			return reader.lines();
	} catch (IOException e) {
			log.severe(() -> e.getMessage());
			return Stream.empty();
		}
	}

	@Override
	public void close() throws Exception {
		connection.disconnect();
	}

}