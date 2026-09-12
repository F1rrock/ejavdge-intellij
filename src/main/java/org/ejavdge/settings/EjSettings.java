package org.ejavdge.settings;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;

public final class EjSettings implements Configurable {
    private static final String PASSWORD_KEY = "org.ejavdge.password";
    private final EjState state;
    private final JBTextField baseUrl;
    private final JBTextField port;
    private final JBTextField clientPath;
    private final JBTextField contestId;
    private final JBTextField login;
    private final JBPasswordField password;
    private String savedPassword;
    private final JPanel panel;

    public EjSettings() {
        this.state = ApplicationManager
            .getApplication()
            .getService(EjState.class);

        this.baseUrl = new JBTextField();
        this.port = new JBTextField();
        this.clientPath = new JBTextField();
        this.contestId = new JBTextField();
        this.login = new JBTextField();
        this.password = new JBPasswordField();

        this.savedPassword = "";

        this.panel = FormBuilder.createFormBuilder()
            .addLabeledComponent("Base URL:", this.baseUrl)
            .addLabeledComponent("Port:", this.port)
            .addLabeledComponent("Client path:", this.clientPath)
            .addLabeledComponent("Contest ID:", this.contestId)
            .addLabeledComponent("Login:", this.login)
            .addLabeledComponent("Password:", this.password)
            .addComponentFillVertically(new JPanel(), 0)
            .getPanel();
    }

    @Override
    public @Nls String getDisplayName() {
        return "EJavdge";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return this.panel;
    }

    @Override
    public boolean isModified() {
        return !this.baseUrl.getText().equals(this.state.baseUrl)
            || !this.port.getText().equals(Integer.toString(this.state.port))
            || !this.clientPath.getText().equals(this.state.clientPath)
            || !this.contestId.getText().equals(Integer.toString(this.state.contestId))
            || !this.login.getText().equals(this.state.login)
            || !new String(this.password.getPassword())
            .equals(this.savedPassword);
    }

    @Override
    public void apply() {
        this.state.baseUrl = this.baseUrl.getText();
        this.state.port = Integer.parseInt(this.port.getText());
        this.state.clientPath = this.clientPath.getText();
        this.state.contestId = Integer.parseInt(this.contestId.getText());
        this.state.login = this.login.getText();

        this.savedPassword = new String(this.password.getPassword());

        PasswordSafe.getInstance().set(
            new CredentialAttributes(PASSWORD_KEY),
            new Credentials(
                this.state.login,
                this.savedPassword
            )
        );
    }

    @Override
    public void reset() {
        this.baseUrl.setText(this.state.baseUrl);
        this.port.setText(Integer.toString(this.state.port));
        this.clientPath.setText(this.state.clientPath);
        this.contestId.setText(Integer.toString(this.state.contestId));
        this.login.setText(this.state.login);

        final Credentials credentials = PasswordSafe
            .getInstance()
            .get(new CredentialAttributes(PASSWORD_KEY));

        this.savedPassword = credentials == null
            || credentials.getPassword() == null
            ? ""
            : String.valueOf(credentials.getPassword());

        this.password.setText(this.savedPassword);
    }

    @Override
    public void disposeUIResources() {
        this.baseUrl.setText("");
        this.port.setText("");
        this.clientPath.setText("");
        this.contestId.setText("");
        this.login.setText("");
        this.password.setText("");
    }
}
