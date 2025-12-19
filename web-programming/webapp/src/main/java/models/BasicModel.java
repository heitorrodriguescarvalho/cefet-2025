package models;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BasicModel {
	private final String hostname;
	private final String username;

	public BasicModel(String hostname, String username) {
		this.hostname = hostname;
		this.username = username;
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	/**
	 * Creates a model populated with the current machine hostname and logged-in user.
	 */
	public static BasicModel fromSystem() {
		String resolvedHost = "unknown-host";
		try {
			resolvedHost = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ignored) {
			// Fallback keeps the example runnable even if hostname cannot be resolved.
		}

		String resolvedUser = System.getProperty("user.name", "unknown-user");
		return new BasicModel(resolvedHost, resolvedUser);
	}
}
