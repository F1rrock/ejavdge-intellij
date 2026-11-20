package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import org.ejavdge.app.*;
import org.ejavdge.app.scenario.SubmittingOfSolution;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.out.preset.EjavdgeErrorDialog;
import org.ejavdge.settings.*;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfSilentSubmit extends ActionWithReport {
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
            new SilentSubmitApp(
                new SubmittingOfSolution(
                    new CurrentFile(e).value(),
                    new LocOfSettings(this.state).value(),
                    new CredOfSettings(this.state).value()
                ),
                new IntellijOut(console)
            ),
            "Silent submit",
            new EjavdgeErrorDialog(project)
        ).actionPerformed(e);
    }
}
