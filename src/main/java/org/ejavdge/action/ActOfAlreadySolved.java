package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AlreadySolved;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;


public final class ActOfAlreadySolved extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(@NotNull AnActionEvent e) {
        new AlreadySolved(
            new LocOfSettings(this.state).value(),
            new CredOfSettings(this.state).value(),
            new IntellijOut(
                new ProjectOf(e)
                    .value()
                    .getService(ConsoleWindow.class)
                    .console()
            )
        ).run();
    }
}
