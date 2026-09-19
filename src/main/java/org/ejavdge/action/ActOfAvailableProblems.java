package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AvailableProblems;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class ActOfAvailableProblems extends AnAction {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void actionPerformed(final @NotNull AnActionEvent event) {
        new AvailableProblems(
            new ResOfSettings(this.state).resource(),
            new IntellijOut(
                Objects.requireNonNull(event.getProject())
                    .getService(ConsoleWindow.class)
                    .console()
            )
        ).run();
    }
}
