package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.ReportedSubmitApp;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfReportedSubmit extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    protected void perform(final @NotNull AnActionEvent e) {
        FileDocumentManager.getInstance().saveAllDocuments();
        final var console = new ProjectOf(e)
            .value()
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        new ReportedSubmitApp(
            new ReportedSubmitApp.Report(
                new CurrentFile(e).value(),
                new LocOfSettings(this.state).value(),
                new CredOfSettings(this.state).value()
            ),
            new IntellijOut(console)
        ).run();
    }
}
