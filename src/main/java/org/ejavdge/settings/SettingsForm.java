package org.ejavdge.settings;

import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;

import javax.swing.JPanel;

public final class SettingsForm {
    private final JBTextField baseUrl = new JBTextField();
    private final JBTextField port = new JBTextField();
    private final JBTextField clientPath = new JBTextField();
    private final JBTextField contestId = new JBTextField();
    private final JBTextField login = new JBTextField();
    private final JBPasswordField password = new JBPasswordField();
    private final JPanel panel = FormBuilder.createFormBuilder()
        .addLabeledComponent("Base URL:", this.baseUrl)
        .addLabeledComponent("Port:", this.port)
        .addLabeledComponent("Client path:", this.clientPath)
        .addLabeledComponent("Contest ID:", this.contestId)
        .addLabeledComponent("Login:", this.login)
        .addLabeledComponent("Password:", this.password)
        .addComponentFillVertically(new JPanel(), 0)
        .getPanel();

    public JPanel panel() {
        return this.panel;
    }

    public SettingsInput input() {
        return new SettingsInput(
            this.baseUrl.getText(),
            this.port.getText(),
            this.clientPath.getText(),
            this.contestId.getText(),
            this.login.getText(),
            new String(this.password.getPassword())
        );
    }

    public void show(final SettingsInput input) {
        this.baseUrl.setText(input.baseUrl());
        this.port.setText(input.port());
        this.clientPath.setText(input.clientPath());
        this.contestId.setText(input.contestId());
        this.login.setText(input.login());
        this.password.setText(input.password());
    }

    public void clear() {
        this.show(new SettingsInput("", "", "", "", "", ""));
    }
}
