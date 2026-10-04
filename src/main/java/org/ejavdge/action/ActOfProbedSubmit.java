package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.*;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.file.JavaProgram;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfProbedSubmit extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    protected void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        final var console = new ProjectOf(e)
            .value()
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new ProbedSubmitApp(
            new ProbedSubmitApp.Report(
                new JavaProgram(
                    new CurrentFile(e).value(),
                    new Text.Of(
                        new ProjectOf(e).value()
                            .getBasePath()
                    )
                ),
                new LocOfSettings(this.state).value(),
                new CredOfSettings(this.state).value()
            ),
            new IntellijOut(console)
        ).run();
    }
}
