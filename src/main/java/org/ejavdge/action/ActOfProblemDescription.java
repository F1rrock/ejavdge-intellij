package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.ProblemDescription;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfProblemDescription extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        new ProblemDescription(
            new ResOfSettings(this.state).value(),
            new CurrentFile(e).value(),
            new IntellijOut(
                new ProjectOf(e)
                    .value()
                    .getService(ConsoleWindow.class)
                    .console()
            )
        ).run();
    }
}
