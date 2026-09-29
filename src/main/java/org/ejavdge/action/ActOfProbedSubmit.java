package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.*;
import org.ejavdge.app.setup.PresetDriver;
import org.ejavdge.auth.Session;
import org.ejavdge.contest.ContestForm;
import org.ejavdge.contest.ContestResource;
import org.ejavdge.domain.run.VerdictOfProbe;
import org.ejavdge.effect.Sequence;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.file.JavaProgram;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.scalar.text.Notice;
import org.ejavdge.scalar.text.Text;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.ejavdge.workspace.out.WritingOf;
import org.jetbrains.annotations.NotNull;

public final class ActOfProbedSubmit extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    protected void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        final var file = new CurrentFile(e).value();
        final var console = new ProjectOf(e)
            .value()
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
        final var out = new IntellijOut(console);
        final var location = new LocOfSettings(this.state).value();
        final var wd = new Text.Of(new ProjectOf(e).value().getBasePath());
        final var session = new Session(
            new PresetDriver(),
            location,
            new CredOfSettings(this.state).value()
        );
        final var resource = new ContestResource(
            new PresetDriver(),
            location,
            session
        );
        new AppOfEffect(
            new Sequence(
                new WritingOf(
                    new Notice(
                        new RunningOf(
                            new AttachmentsDownload(
                                resource,
                                file,
                                wd
                            )
                        ),
                        new Text.Of("Downloaded successfully!")
                    ),
                    out
                ),
                new RunningOf(
                    new ProbedSubmit(
                        new VerdictOfProbe(
                            new JavaProgram(file, wd),
                            resource
                        ),
                        new ReportedSubmit(
                            new SubmitWithNotification(
                                new SilentSubmit(
                                    new CurrentFile(e).value(),
                                    new ContestForm(
                                        new PresetDriver(),
                                        location,
                                        session
                                    ),
                                    resource
                                ),
                                resource,
                                out
                            ),
                            new LastReport(
                                file,
                                resource,
                                out
                            )
                        ),
                        out
                    )
                )
            )
        ).run();
    }
}
