package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import org.ejavdge.app.LastReportApp;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfLastReport extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        FileDocumentManager.getInstance().saveAllDocuments();
        final var console = new ProjectOf(e)
            .value()
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new LastReportApp(
            new CurrentFile(e).value(),
            new ResOfSettings(this.state).value(),
            new IntellijOut(console)
        ).run();
    }
}
