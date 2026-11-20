package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import org.ejavdge.app.AvailableProblemsApp;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijErrorDialog;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.out.preset.EjavdgeErrorDialog;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfAvailableProblems extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        FileDocumentManager.getInstance().saveAllDocuments();
        final var project = new ProjectOf(e).value();
        final var console = project
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new BackgroundAction(
            new AvailableProblemsApp(
                new LocOfSettings(this.state).value(),
                new CredOfSettings(this.state).value(),
                new IntellijOut(console)
            ),
            "Available problems",
            new EjavdgeErrorDialog(project)
        ).actionPerformed(e);
    }
}
