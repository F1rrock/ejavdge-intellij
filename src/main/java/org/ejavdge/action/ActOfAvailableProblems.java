package org.ejavdge.action;

import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AvailableProblems;
import org.ejavdge.out.IntellijConsole;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.ejavdge.workspace.out.WithReport;
import org.ejavdge.workspace.out.WithoutAnsi;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ActOfAvailableProblems extends AnAction {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void actionPerformed(final @NotNull AnActionEvent event) {
        final var console = Objects.requireNonNull(
                event.getProject()
            )
            .getService(ConsoleWindow.class)
            .console();
        new AvailableProblems(
            new LocOfSettings(this.state).location(),
            new CredOfSettings(this.state).credentials(),
            new WithReport(
                new WithoutAnsi(
                    new IntellijConsole(console)
                ),
                new WithoutAnsi(
                    new IntellijConsole(
                        console,
                        ConsoleViewContentType.ERROR_OUTPUT
                    )
                )
            )
        ).run();
    }
}
