package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.AppOfEffect;
import org.ejavdge.app.AttachmentsDownload;
import org.ejavdge.app.LocalProbe;
import org.ejavdge.app.RunningOf;
import org.ejavdge.effect.Sequence;
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
        final var resource = new ResOfSettings(this.state).value();
        final var file = new CurrentFile(e).value();
        final var wd = new Text.Of(project.getBasePath());
        new AppOfEffect(
            new Sequence(
                new RunningOf(
                    new AttachmentsDownload(
                        resource,
                        file,
                        wd
                    )
                ),
                new RunningOf(
                    new LocalProbe(
                        new JavaProgram(file, wd),
                        resource,
                        new IntellijOut(
                            project
                                .getService(ConsoleWindow.class)
                                .console()
                        )
                    )
                )
            )
        ).run();
    }
}
