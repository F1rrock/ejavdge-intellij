package org.ejavdge.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

public final class EjSettings implements Configurable {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);
    private final SettingsForm form = new SettingsForm();
    private String savedPassword = "";

    @Override
    public @Nls String getDisplayName() {
        return "EJavdge";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return this.form.panel();
    }

    @Override
    public boolean isModified() {
        return !this.form.input()
            .equals(new EjConfiguration(this.state, this.savedPassword).input());
    }

    @Override
    public void apply() {
        final SettingsInput input = this.form.input();
        new SaveSettings(this.state, input).run();
        this.savedPassword = input.password();
    }

    @Override
    public void reset() {
        final String password = new SettingsPassword().value();
        this.savedPassword = password == null ? "" : password;
        this.form.show(new EjConfiguration(this.state, this.savedPassword).input());
    }

    @Override
    public void disposeUIResources() {
        this.form.clear();
    }
}
