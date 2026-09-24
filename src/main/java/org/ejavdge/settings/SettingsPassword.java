package org.ejavdge.settings;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import org.jetbrains.annotations.Nullable;

public final class SettingsPassword {
    private final CredentialAttributes attributes =
        new CredentialAttributes("org.ejavdge.password");

    public @Nullable String value() {
        final Credentials saved = PasswordSafe
            .getInstance()
            .get(this.attributes);
        return saved == null || saved.getPassword() == null
            ? null
            : String.valueOf(saved.getPassword());
    }

    public void save(final String login, final String password) {
        PasswordSafe.getInstance().set(
            this.attributes,
            new Credentials(login, password)
        );
    }
}
