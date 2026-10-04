package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.LocalProbeApp;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.file.JavaProgram;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfLocalProbe extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        final var project = new ProjectOf(e).value();
        final var console = project
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new LocalProbeApp(
            new JavaProgram(
                new CurrentFile(e).value(),
                new Text.Of(project.getBasePath())
            ),
            new ResOfSettings(this.state).value(),
            new IntellijOut(console)
        ).run();
    }
}
