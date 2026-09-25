package org.ejavdge.settings;

import org.ejavdge.scalar.IntellijScalar;
import org.ejavdge.error.IntellijError;
import org.ejavdge.web.context.Credentials;

public final class CredOfSettings implements IntellijScalar<Credentials> {
    private final EjState state;

    public CredOfSettings(final EjState s) {
        this.state = s;
    }

    @Override
    public Credentials value() throws IntellijError {
        final String password = new SettingsPassword().value();
        if (password == null) {
            throw new IntellijError(
                "Credentials could not be obtained because the password is not set."
            );
        }
        return new EjConfiguration(this.state, password).credentials();
    }
}
