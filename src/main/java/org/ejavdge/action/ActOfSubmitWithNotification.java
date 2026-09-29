package org.ejavdge.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.ejavdge.app.SilentSubmit;
import org.ejavdge.app.SubmitWithNotification;
import org.ejavdge.app.setup.PresetDriver;
import org.ejavdge.auth.Session;
import org.ejavdge.contest.ContestForm;
import org.ejavdge.contest.ContestResource;
import org.ejavdge.event.CurrentFile;
import org.ejavdge.event.ProjectOf;
import org.ejavdge.out.IntellijOut;
import org.ejavdge.settings.CredOfSettings;
import org.ejavdge.settings.EjState;
import org.ejavdge.settings.LocOfSettings;
import org.ejavdge.widget.ConsoleWindow;
import org.jetbrains.annotations.NotNull;

public final class ActOfSubmitWithNotification extends ActionWithReport {
    private final EjState state = ApplicationManager
        .getApplication()
        .getService(EjState.class);

    @Override
    protected void perform(final @NotNull AnActionEvent e) {
        ApplicationManager.getApplication().saveAll();
        final var location = new LocOfSettings(this.state).value();
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
        final var console = new ProjectOf(e)
            .value()
            .getService(ConsoleWindow.class)
            .console();
        console.clear();
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
            new IntellijOut(console)
        ).run();
    }
}
