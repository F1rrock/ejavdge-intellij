package org.ejavdge.settings;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.ide.passwordSafe.PasswordSafe;
import org.ejavdge.error.InvariantViolation;
import org.ejavdge.scalar.num.Num;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.web.context.Credentials;

public final class CredOfSettings {
    private static final String PASSWORD_KEY = "org.ejavdge.password";
    private final EjState state;

    public CredOfSettings(final EjState s) {
        this.state = s;
    }

    public Credentials credentials() throws InvariantViolation {
        final var saved = PasswordSafe
            .getInstance()
            .get(new CredentialAttributes(PASSWORD_KEY));
        if (saved == null || saved.getPassword() == null) {
            throw new InvariantViolation(
                "Credentials could not be obtained because the password is not set."
            );
        }
        return new Credentials(
            new Text.Of(this.state.login),
            new Text.Of(String.valueOf(saved.getPassword())),
            new Num.Of(this.state.contestId)
        );
    }
}
