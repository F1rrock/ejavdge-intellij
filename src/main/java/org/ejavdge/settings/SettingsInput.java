package org.ejavdge.settings;

public record SettingsInput(
    String baseUrl,
    String port,
    String clientPath,
    String contestId,
    String login,
    String password
) {
}
