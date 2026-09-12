package org.ejavdge.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(
    name = "EjSettings",
    storages = @Storage("ejavdge.xml")
)
@SuppressWarnings("java:S1104")
public final class EjState implements PersistentStateComponent<EjState> {
    private static final String CLIENT_PATH = "/new-client";
    public String baseUrl;
    public int port;
    public String clientPath;
    public int contestId;
    public String login;

    public EjState() {
        this.baseUrl = "10.21.17.68";
        this.port = 80;
        this.clientPath = CLIENT_PATH;
        this.contestId = 1;
        this.login = "";
    }

    @Override
    public @NotNull EjState getState() {
        return this;
    }

    @Override
    public void loadState(final @NotNull EjState state) {
        this.baseUrl = state.baseUrl;
        this.port = state.port;
        this.clientPath = state.clientPath;
        this.contestId = state.contestId;
        this.login = state.login;
    }
}
