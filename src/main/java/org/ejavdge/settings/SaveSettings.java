package org.ejavdge.settings;

public final class SaveSettings {
    private final EjState state;
    private final SettingsInput input;

    public SaveSettings(final EjState state, final SettingsInput input) {
        this.state = state;
        this.input = input;
    }

    public void run() {
        new EjConfiguration(this.input).writeTo(this.state);
        new SettingsPassword().save(this.input.login(), this.input.password());
    }
}
