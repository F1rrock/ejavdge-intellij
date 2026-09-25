package org.ejavdge.settings;

import org.ejavdge.scalar.num.Num;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.web.context.Credentials;
import org.ejavdge.web.context.Location;

public final class EjConfiguration {
    private final String baseUrl;
    private final int port;
    private final String clientPath;
    private final int contestId;
    private final String login;
    private final String password;

    public EjConfiguration() {
        this("10.21.17.68", 80, "/new-client", 1, "", "");
    }

    public EjConfiguration(final EjState state, final String password) {
        this(
            state.baseUrl, state.port, state.clientPath,
            state.contestId, state.login, password
        );
    }

    public EjConfiguration(final SettingsInput input) {
        this(
            input.baseUrl(),
            Integer.parseInt(input.port()),
            input.clientPath(),
            Integer.parseInt(input.contestId()),
            input.login(),
            input.password()
        );
    }

    private EjConfiguration(
        final String baseUrl,
        final int port,
        final String clientPath,
        final int contestId,
        final String login,
        final String password
    ) {
        this.baseUrl = baseUrl;
        this.port = port;
        this.clientPath = clientPath;
        this.contestId = contestId;
        this.login = login;
        this.password = password;
    }

    public SettingsInput input() {
        return new SettingsInput(
            this.baseUrl,
            Integer.toString(this.port),
            this.clientPath,
            Integer.toString(this.contestId),
            this.login,
            this.password
        );
    }

    public void writeTo(final EjState state) {
        state.baseUrl = this.baseUrl;
        state.port = this.port;
        state.clientPath = this.clientPath;
        state.contestId = this.contestId;
        state.login = this.login;
    }

    public Location location() {
        return new Location(
            new Text.Of(this.clientPath),
            new Text.Of(this.baseUrl),
            new Num.Of(this.port)
        );
    }

    public Credentials credentials() {
        return new Credentials(
            new Text.Of(this.login),
            new Text.Of(this.password),
            new Num.Of(this.contestId)
        );
    }
}
