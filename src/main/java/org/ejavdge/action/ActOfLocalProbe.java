package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import org.ejavdge.app.LocalProbeApp;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.event.SourceRootOf;
import org.ejavdge.file.JavaProgram;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.out.preset.EjavdgeErrorDialog;
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
        FileDocumentManager.getInstance().saveAllDocuments();
        final var project = new ProjectOf(e).value();
        final var console = project
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new BackgroundAction(
            new LocalProbeApp(
                new JavaProgram(
                    new CurrentFile(e).value(),
                    new SourceRootOf(e).value()
                ),
                new ResOfSettings(this.state).value(),
                new IntellijOut(console)
            ),
            "Local probe",
            new EjavdgeErrorDialog(project)
        ).actionPerformed(e);
    }
}
