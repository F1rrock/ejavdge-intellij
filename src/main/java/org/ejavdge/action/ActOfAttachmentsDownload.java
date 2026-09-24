package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AppOfEffect;
import org.ejavdge.app.AttachmentsDownload;
import org.ejavdge.app.RunningOf;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.scalar.text.Notice;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.ResOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.ejavdge.workspace.out.WritingOf;
import org.jetbrains.annotations.NotNull;

public final class ActOfAttachmentsDownload extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    public void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        final var project = new ProjectOf(e).value();
        new AppOfEffect(
            new WritingOf(
                new Notice(
                    new RunningOf(
                        new AttachmentsDownload(
                            new ResOfSettings(this.state).value(),
                            new CurrentFile(e).value(),
                            new Text.Of(project.getBasePath())
                        )
                    ),
                    new Text.Of("Downloaded successfully!")
                ),
                new IntellijOut(
                    project
                        .getService(ConsoleWindow.class)
                        .console()
                )
            )
        ).run();
    }
}
